package org.mtr.mapping.render.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.mtr.mapping.holder.Matrix4f;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.object.VertexArray;
import org.mtr.mapping.render.vertex.VertexAttributeMapping;

public final class RawModel {
   private final Map<MaterialProperties, RawMesh> materialPropertiesToRawMesh = new HashMap<>();

   public List<VertexArray> upload(VertexAttributeMapping mapping) {
      List<VertexArray> vertexArrays = new ArrayList<>();
      this.iterateRawMeshList(rawMesh -> {
         if (rawMesh.hasFaces()) {
            vertexArrays.add(new VertexArray(rawMesh.upload(mapping), mapping));
         }
      });
      return vertexArrays;
   }

   public void append(RawMesh nextMesh) {
      if (this.materialPropertiesToRawMesh.containsKey(nextMesh.materialProperties)) {
         this.materialPropertiesToRawMesh.get(nextMesh.materialProperties).append(nextMesh);
      } else {
         RawMesh newMesh = new RawMesh(nextMesh.materialProperties);
         this.materialPropertiesToRawMesh.put(nextMesh.materialProperties, newMesh);
         newMesh.append(nextMesh);
      }
   }

   public RawMesh getRawMesh(MaterialProperties materialProperties) {
      return this.materialPropertiesToRawMesh.computeIfAbsent(materialProperties, RawMesh::new);
   }

   public void iterateRawMeshList(Consumer<RawMesh> consumer) {
      this.materialPropertiesToRawMesh.values().forEach(consumer);
   }

   public void append(Collection<RawMesh> nextMesh) {
      nextMesh.forEach(this::append);
   }

   public void append(RawModel nextModel) {
      this.append(nextModel.materialPropertiesToRawMesh.values());
   }

   public void applyMatrix(Matrix4f matrix4f) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyMatrix(matrix4f));
   }

   public void applyTranslation(float x, float y, float z) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyTranslation(x, y, z));
   }

   public void applyRotation(Vector3f axis, float angle) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyRotation(axis, angle));
   }

   public void applyScale(float x, float y, float z) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyScale(x, y, z));
   }

   public void applyMirror(boolean vx, boolean vy, boolean vz, boolean nx, boolean ny, boolean nz) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyMirror(vx, vy, vz, nx, ny, nz));
   }

   public void applyUVMirror(boolean u, boolean v) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyUVMirror(u, v));
   }

   public void generateNormals() {
      this.iterateRawMeshList(RawMesh::generateNormals);
   }

   public void distinct() {
      this.iterateRawMeshList(RawMesh::distinct);
   }

   public void triangulate() {
      this.iterateRawMeshList(RawMesh::triangulate);
   }

   public void applyShear(Vector3f dir, Vector3f shear, float ratio) {
      this.iterateRawMeshList(rawMesh -> rawMesh.applyShear(dir, shear, ratio));
   }
}
