package org.mtr.mapping.render.model;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import org.joml.Vector3fc;
import org.mtr.mapping.holder.Matrix4f;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.object.IndexBuffer;
import org.mtr.mapping.render.object.VertexBuffer;
import org.mtr.mapping.render.tool.OffHeapAllocator;
import org.mtr.mapping.render.tool.Utilities;
import org.mtr.mapping.render.vertex.Vertex;
import org.mtr.mapping.render.vertex.VertexAttributeMapping;
import org.mtr.mapping.render.vertex.VertexAttributeSource;
import org.mtr.mapping.render.vertex.VertexAttributeType;
import org.mtr.mapping.tool.DummyClass;

public final class RawMesh {
   public final MaterialProperties materialProperties;
   public final List<Vertex> vertices = new ArrayList<>();
   public final List<Face> faces = new ArrayList<>();

   public RawMesh(MaterialProperties materialProperties) {
      this.materialProperties = materialProperties;
   }

   public RawMesh(OptimizedModel.ShaderType shaderType, RawMesh rawMesh) {
      if (rawMesh.materialProperties.shaderType == OptimizedModel.ShaderType.CUTOUT) {
         this.materialProperties = new MaterialProperties(
            shaderType, rawMesh.materialProperties.getTexture(), rawMesh.materialProperties.vertexAttributeState.color
         );
      } else {
         this.materialProperties = rawMesh.materialProperties;
      }

      rawMesh.vertices.forEach(vertex -> this.vertices.add(new Vertex(vertex)));
      rawMesh.faces.forEach(face -> this.faces.add(new Face(face)));
   }

   public void append(RawMesh nextMesh) {
      if (nextMesh == this) {
         IllegalStateException e = new IllegalStateException("Mesh self-appending");
         DummyClass.logException(e);
         throw e;
      } else {
         int vertOffset = this.vertices.size();
         this.vertices.addAll(nextMesh.vertices);
         nextMesh.faces.forEach(face -> {
            Face newFace = new Face(face);

            for (int i = 0; i < newFace.vertices.length; i++) {
               newFace.vertices[i] = newFace.vertices[i] + vertOffset;
            }

            this.faces.add(newFace);
         });
      }
   }

   public void clear() {
      this.vertices.clear();
      this.faces.clear();
   }

   public void addVertex(Vertex vertex) {
      this.vertices.add(vertex);
      if (this.vertices.size() % 4 == 0) {
         this.faces.add(new Face(IntStream.range(this.vertices.size() - 4, this.vertices.size()).toArray()));
      }
   }

   public boolean hasFaces() {
      return !this.faces.isEmpty();
   }

   public void validateVertexIndex() {
      for (Face face : this.faces) {
         for (int vertexIndex : face.vertices) {
            if (vertexIndex < 0 || vertexIndex >= this.vertices.size()) {
               IndexOutOfBoundsException e = new IndexOutOfBoundsException(
                  String.format("RawMesh contains invalid vertex index %s (should be 0 to %s)", vertexIndex, this.vertices.size() - 1)
               );
               DummyClass.logException(e);
               throw e;
            }
         }
      }
   }

   public void triangulate() {
      List<Face> newFaces = new ArrayList<>();
      this.faces.forEach(face -> newFaces.addAll(Face.triangulate(face.vertices)));
      this.faces.clear();
      this.faces.addAll(newFaces);
   }

   public void distinct() {
      List<Vertex> distinctVertices = new ArrayList<>(this.vertices.size());
      Map<Vertex, Integer> verticesLookup = new HashMap<>(this.vertices.size());
      Set<Face> distinctFaces = new HashSet<>(this.faces.size());
      this.faces.forEach(face -> {
         for (int i = 0; i < face.vertices.length; i++) {
            Vertex vertex = this.vertices.get(face.vertices[i]);
            int newIndex;
            if (verticesLookup.containsKey(vertex)) {
               newIndex = verticesLookup.get(vertex);
            } else {
               distinctVertices.add(vertex);
               newIndex = distinctVertices.size() - 1;
               verticesLookup.put(vertex, newIndex);
            }

            face.vertices[i] = newIndex;
         }

         distinctFaces.add(face);
      });
      this.vertices.clear();
      this.vertices.addAll(distinctVertices);
      this.faces.clear();
      this.faces.addAll(distinctFaces);
   }

   public void generateNormals() {
      List<Vertex> newVertices = new ArrayList<>(this.vertices.size());
      this.faces.forEach(face -> {
         if (face.vertices.length >= 3) {
            int i0 = face.vertices[0];
            int i1 = face.vertices[1];
            int i2 = face.vertices[2];
            double ax = this.vertices.get(i1).position.getX() - this.vertices.get(i0).position.getX();
            double ay = this.vertices.get(i1).position.getY() - this.vertices.get(i0).position.getY();
            double az = this.vertices.get(i1).position.getZ() - this.vertices.get(i0).position.getZ();
            double bx = this.vertices.get(i2).position.getX() - this.vertices.get(i0).position.getX();
            double by = this.vertices.get(i2).position.getY() - this.vertices.get(i0).position.getY();
            double bz = this.vertices.get(i2).position.getZ() - this.vertices.get(i0).position.getZ();
            double nx = ay * bz - az * by;
            double ny = az * bx - ax * bz;
            double nz = ax * by - ay * bx;
            double t1 = nx * nx + ny * ny + nz * nz;
            if (t1 != 0.0) {
               double t2 = 1.0 / Math.sqrt(t1);
               float mx = (float)(nx * t2);
               float my = (float)(ny * t2);
               float mz = (float)(nz * t2);

               for (int j = 0; j < face.vertices.length; j++) {
                  Vertex newVertex = new Vertex(this.vertices.get(face.vertices[j]));
                  if (vectorIsZero(newVertex.normal)) {
                     newVertex.normal = new Vector3f(mx, my, mz);
                  }

                  newVertices.add(newVertex);
                  face.vertices[j] = newVertices.size() - 1;
               }
            } else {
               for (int i = 0; i < face.vertices.length; i++) {
                  Vertex newVertex = new Vertex(this.vertices.get(face.vertices[i]));
                  if (vectorIsZero(this.vertices.get(face.vertices[i]).normal)) {
                     newVertex.normal = new Vector3f(0.0F, 1.0F, 0.0F);
                  }

                  newVertices.add(newVertex);
                  face.vertices[i] = newVertices.size() - 1;
               }
            }
         }
      });
      this.vertices.clear();
      this.vertices.addAll(newVertices);
   }

   public void upload(Mesh mesh, VertexAttributeMapping mapping) {
      this.distinct();
      ByteBuffer byteBuffer = OffHeapAllocator.allocate(this.vertices.size() * mapping.strideVertex);
      this.vertices.forEach(vertex -> {
         if (shouldWriteVertexBuffer(mapping, VertexAttributeType.POSITION)) {
            Vector3f position = vertex.position;
            byteBuffer.putFloat(position.getX()).putFloat(position.getY()).putFloat(position.getZ());
         }

         if (shouldWriteVertexBuffer(mapping, VertexAttributeType.COLOR)) {
            byteBuffer.putInt(vertex.color);
         }

         if (shouldWriteVertexBuffer(mapping, VertexAttributeType.UV_TEXTURE)) {
            byteBuffer.putFloat(vertex.u).putFloat(vertex.v);
         }

         if (shouldWriteVertexBuffer(mapping, VertexAttributeType.UV_LIGHTMAP)) {
            byteBuffer.putInt(vertex.light);
         }

         if (shouldWriteVertexBuffer(mapping, VertexAttributeType.NORMAL)) {
            Vector3f normal = Utilities.copy(vertex.normal);
            ((org.joml.Vector3f)normal.data).normalize();
            byteBuffer.put((byte)(normal.getX() * 127.0F)).put((byte)(normal.getY() * 127.0F)).put((byte)(normal.getZ() * 127.0F));
         }

         if (mapping.paddingVertex > 0) {
            byteBuffer.put((byte)0);
         }
      });
      mesh.vertexBuffer.upload(byteBuffer, 35044);
      OffHeapAllocator.free(byteBuffer);
      ByteBuffer indexBuffer = OffHeapAllocator.allocate(this.faces.size() * 3 * 4);
      this.faces.forEach(face -> {
         for (int j = 0; j < face.vertices.length; j++) {
            indexBuffer.putInt(face.vertices[j]);
         }
      });
      mesh.indexBuffer.upload(indexBuffer, 35044);
      mesh.indexBuffer.setFaceCount(this.faces.size());
      OffHeapAllocator.free(indexBuffer);
   }

   public Mesh upload(VertexAttributeMapping vertexAttributeMapping) {
      this.validateVertexIndex();
      Mesh mesh = new Mesh(new VertexBuffer(), new IndexBuffer(this.faces.size(), 5125), this.materialProperties);
      this.upload(mesh, vertexAttributeMapping);
      return mesh;
   }

   private static boolean shouldWriteVertexBuffer(VertexAttributeMapping vertexAttributeMapping, VertexAttributeType vertexAttributeType) {
      return vertexAttributeMapping.sources.get(vertexAttributeType) == VertexAttributeSource.VERTEX_BUFFER;
   }

   private static int getVertexBufferPosition(VertexAttributeMapping vertexAttributeMapping, int vertexId, VertexAttributeType vertexAttributeType) {
      return vertexAttributeMapping.strideVertex * vertexId + vertexAttributeMapping.pointers.get(vertexAttributeType);
   }

   private static boolean vectorIsZero(Vector3f vector3f) {
      return vector3f.getX() == 0.0F && vector3f.getY() == 0.0F && vector3f.getZ() == 0.0F;
   }

   public void applyMatrix(Matrix4f matrix4f) {
      this.vertices.forEach(vertex -> {
         vertex.position = Utilities.transformPosition(matrix4f, vertex.position);
         vertex.normal = Utilities.transformDirection(matrix4f, vertex.normal);
      });
   }

   public void applyTranslation(float x, float y, float z) {
      this.vertices.forEach(vertex -> ((org.joml.Vector3f)vertex.position.data).add(x, y, z));
   }

   public void applyRotation(Vector3f axis, float angle) {
      this.vertices.forEach(vertex -> {
         ((org.joml.Vector3f)vertex.position.data).rotateAxis((float)Math.toRadians(angle), axis.getX(), axis.getY(), axis.getZ());
         ((org.joml.Vector3f)vertex.normal.data).rotateAxis((float)Math.toRadians(angle), axis.getX(), axis.getY(), axis.getZ());
      });
   }

   public void applyScale(float x, float y, float z) {
      float rx = 1.0F / x;
      float ry = 1.0F / y;
      float rz = 1.0F / z;
      float rx2 = rx * rx;
      float ry2 = ry * ry;
      float rz2 = rz * rz;
      boolean reverse = x * y * z < 0.0F;
      this.vertices.forEach(vertex -> {
         ((org.joml.Vector3f)vertex.position.data).mul(x, y, z);
         float nx2 = vertex.normal.getX() * vertex.normal.getX();
         float ny2 = vertex.normal.getY() * vertex.normal.getY();
         float nz2 = vertex.normal.getZ() * vertex.normal.getZ();
         float u1 = nx2 * rx2 + ny2 * ry2 + nz2 * rz2;
         if (u1 != 0.0F) {
            float u2 = (float)Math.sqrt((nx2 + ny2 + nz2) / u1);
            ((org.joml.Vector3f)vertex.normal.data).mul(rx * u2, ry * u2, rz * u2);
         }
      });
      if (reverse) {
         this.faces.forEach(Face::flip);
      }
   }

   public void applyMirror(boolean vx, boolean vy, boolean vz, boolean nx, boolean ny, boolean nz) {
      this.vertices.forEach(vertex -> {
         ((org.joml.Vector3f)vertex.position.data).mul(vx ? -1.0F : 1.0F, vy ? -1.0F : 1.0F, vz ? -1.0F : 1.0F);
         ((org.joml.Vector3f)vertex.normal.data).mul(nx ? -1.0F : 1.0F, ny ? -1.0F : 1.0F, nz ? -1.0F : 1.0F);
      });
      int numFlips = 0;
      if (vx) {
         numFlips++;
      }

      if (vy) {
         numFlips++;
      }

      if (vz) {
         numFlips++;
      }

      if (numFlips % 2 != 0) {
         this.faces.forEach(Face::flip);
      }
   }

   public void applyUVMirror(boolean u, boolean v) {
      this.vertices.forEach(vertex -> {
         if (u) {
            vertex.u = 1.0F - vertex.u;
         }

         if (v) {
            vertex.v = 1.0F - vertex.v;
         }
      });
   }

   public void applyShear(Vector3f dir, Vector3f shear, float ratio) {
      this.vertices.forEach(vertex -> {
         float n1 = ratio * (dir.getX() * vertex.position.getX() + dir.getY() * vertex.position.getY() + dir.getZ() * vertex.position.getZ());
         Vector3f offset1 = Utilities.copy(shear);
         ((org.joml.Vector3f)offset1.data).mul(n1);
         ((org.joml.Vector3f)vertex.position.data).add((Vector3fc)offset1.data);
         if (!vectorIsZero(vertex.normal)) {
            float n2 = ratio * (shear.getX() * vertex.normal.getX() + shear.getY() * vertex.normal.getY() + shear.getZ() * vertex.normal.getZ());
            Vector3f offset2 = Utilities.copy(dir);
            ((org.joml.Vector3f)offset2.data).mul(-n2);
            ((org.joml.Vector3f)vertex.normal.data).add((Vector3fc)offset2.data);
            ((org.joml.Vector3f)vertex.normal.data).normalize();
         }
      });
   }
}
