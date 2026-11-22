package org.mtr.mapping.render.obj;

import de.javagl.obj.FloatTuple;
import de.javagl.obj.Mtl;
import de.javagl.obj.MtlReader;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjSplitting;
import de.javagl.obj.ObjUtils;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.model.Face;
import org.mtr.mapping.render.model.RawMesh;
import org.mtr.mapping.render.vertex.Vertex;
import org.mtr.mapping.tool.DummyClass;

public final class ObjModelLoader {
   public static Map<String, List<RawMesh>> loadModel(
      String objString, Function<String, String> mtlResolver, Function<String, Identifier> textureResolver, AtlasManager atlasManager, boolean splitModel
   ) {
      Map<String, List<RawMesh>> result = new HashMap<>();

      try {
         Obj sourceObj = ObjReader.read(IOUtils.toInputStream(objString, StandardCharsets.UTF_8));
         Map<String, Mtl> materials = new HashMap<>();
         sourceObj.getMtlFileNames()
            .forEach(
               mtlFileName -> {
                  try {
                     MtlReader.read(IOUtils.toInputStream(mtlResolver.apply(mtlFileName.replace("\\\\", "/").replace("\\", "/")), StandardCharsets.UTF_8))
                        .forEach(mtl -> materials.put(mtl.getName(), mtl));
                  } catch (Exception var4x) {
                     DummyClass.logException(var4x);
                  }
               }
            );
         if (splitModel) {
            ObjSplitting.splitByGroups(sourceObj).forEach((key, obj) -> result.put(key, loadModel(obj, materials, textureResolver, atlasManager)));
         } else {
            result.put("", loadModel(sourceObj, materials, textureResolver, atlasManager));
         }
      } catch (Exception var8) {
         DummyClass.logException(var8);
      }

      return result;
   }

   private static List<RawMesh> loadModel(Obj sourceObj, Map<String, Mtl> materials, Function<String, Identifier> textureResolver, AtlasManager atlasManager) {
      List<RawMesh> rawMeshes = new ArrayList<>();
      Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(sourceObj);
      if (materialGroups.isEmpty() && sourceObj.getNumFaces() > 0) {
         materialGroups.put("", sourceObj);
      }

      materialGroups.forEach(
         (key, obj) -> {
            if (obj.getNumFaces() > 0) {
               Map<String, String> materialOptions = splitMaterialOptions(key);
               String materialGroupName = materialOptions.get("");
               OptimizedModel.ShaderType shaderType = legacyMapping(materialOptions.getOrDefault("#", ""));
               boolean flipTextureV = materialOptions.getOrDefault("flipv", "0").equals("1");
               String texture;
               Integer color;
               if (!materials.isEmpty()) {
                  Mtl objMaterial = materials.getOrDefault(key, null);
                  if (objMaterial == null) {
                     texture = "";
                     color = null;
                  } else {
                     if (StringUtils.isEmpty(objMaterial.getMapKd())) {
                        texture = "";
                     } else {
                        texture = objMaterial.getMapKd();
                     }

                     FloatTuple kd = objMaterial.getKd();
                     color = kd == null
                        ? mergeColor(255, 255, 255, 255)
                        : mergeColor(
                           (int)(kd.getX() * 255.0F),
                           (int)(kd.getY() * 255.0F),
                           (int)(kd.getZ() * 255.0F),
                           objMaterial.getD() == null ? 255 : (int)(objMaterial.getD() * 255.0F)
                        );
                  }
               } else {
                  texture = materialGroupName.equals("_") ? "" : materialGroupName;
                  color = mergeColor(255, 255, 255, 255);
               }

               Obj renderObjMesh = ObjUtils.convertToRenderable(obj);
               RawMesh mesh = new RawMesh(new MaterialProperties(shaderType, textureResolver.apply(texture.replace("\\\\", "/").replace("\\", "/")), color));

               for (int i = 0; i < renderObjMesh.getNumVertices(); i++) {
                  FloatTuple pos = renderObjMesh.getVertex(i);
                  FloatTuple normal;
                  if (i < renderObjMesh.getNumNormals()) {
                     normal = renderObjMesh.getNormal(i);
                  } else {
                     normal = ObjModelLoader.ZeroFloatTuple.ZERO3;
                  }

                  FloatTuple uv;
                  if (i < renderObjMesh.getNumTexCoords()) {
                     uv = renderObjMesh.getTexCoord(i);
                  } else {
                     uv = ObjModelLoader.ZeroFloatTuple.ZERO2;
                  }

                  Vertex seVertex = new Vertex();
                  seVertex.position = new Vector3f(pos.getX(), pos.getY(), pos.getZ());
                  seVertex.normal = new Vector3f(normal.getX(), normal.getY(), normal.getZ());
                  seVertex.u = uv.getX();
                  seVertex.v = flipTextureV ? 1.0F - uv.getY() : uv.getY();
                  mesh.vertices.add(seVertex);
               }

               for (int i = 0; i < renderObjMesh.getNumFaces(); i++) {
                  ObjFace face = renderObjMesh.getFace(i);
                  mesh.faces.add(new Face(new int[]{face.getVertexIndex(0), face.getVertexIndex(1), face.getVertexIndex(2)}));
               }

               if (atlasManager != null) {
                  atlasManager.applyToMesh(mesh);
               }

               mesh.validateVertexIndex();
               rawMeshes.add(mesh);
            }
         }
      );
      return rawMeshes;
   }

   private static Map<String, String> splitMaterialOptions(String source) {
      Map<String, String> result = new HashMap<>();
      String[] majorParts = source.split("#", 2);
      result.put("", majorParts[0]);
      if (majorParts.length > 1) {
         for (String minorPart : majorParts[1].split(",")) {
            String[] tokens = minorPart.split("=", 2);
            if (tokens.length > 1) {
               result.put(tokens[0], tokens[1]);
            } else if (!result.containsKey("#")) {
               result.put("#", tokens[0]);
            } else {
               result.put(tokens[0].toLowerCase(Locale.ROOT), "1");
            }
         }
      }

      return result;
   }

   private static int mergeColor(int r, int g, int b, int a) {
      return r << 24 | g << 16 | b << 8 | a;
   }

   public static Identifier resolveRelativePath(Identifier baseFile, String relative, @Nullable String expectExtension) {
      String result = relative.toLowerCase(Locale.ROOT).replace('\\', '/');
      if (result.contains(":")) {
         result = result.replaceAll("[^a-z0-9/.:_-]", "_");
         return Identifier.parse(result);
      } else {
         result = result.replaceAll("[^a-z0-9/._-]", "_");
         if (result.endsWith(".jpg") || result.endsWith(".bmp") || result.endsWith(".tga")) {
            result = result.substring(0, result.length() - 4) + ".png";
         }

         if (expectExtension != null && !result.endsWith(expectExtension)) {
            result = result + expectExtension;
         }

         return Identifier.fromNamespaceAndPath(
            baseFile.getNamespace(), FileSystems.getDefault().getPath(baseFile.getPath()).getParent().resolve(result).normalize().toString().replace('\\', '/')
         );
      }
   }

   private static OptimizedModel.ShaderType legacyMapping(String type) {
      String var1 = type.toLowerCase(Locale.ENGLISH);
      switch (var1) {
         case "exteriortranslucent":
            return OptimizedModel.ShaderType.TRANSLUCENT;
         case "light":
            return OptimizedModel.ShaderType.CUTOUT_GLOWING;
         case "always_on_light":
         case "lighttranslucent":
            return OptimizedModel.ShaderType.TRANSLUCENT_GLOWING;
         case "interior":
            return OptimizedModel.ShaderType.CUTOUT_BRIGHT;
         case "interior_translucent":
         case "interiortranslucent":
            return OptimizedModel.ShaderType.TRANSLUCENT_BRIGHT;
         default:
            return OptimizedModel.ShaderType.CUTOUT;
      }
   }

   private static class ZeroFloatTuple implements FloatTuple {
      private final int dimensions;
      public static final ObjModelLoader.ZeroFloatTuple ZERO2 = new ObjModelLoader.ZeroFloatTuple(2);
      public static final ObjModelLoader.ZeroFloatTuple ZERO3 = new ObjModelLoader.ZeroFloatTuple(3);

      public ZeroFloatTuple(int dimensions) {
         this.dimensions = dimensions;
      }

      public float getX() {
         return 0.0F;
      }

      public float getY() {
         return 0.0F;
      }

      public float getZ() {
         return 0.0F;
      }

      public float getW() {
         return 0.0F;
      }

      public float get(int index) {
         return 0.0F;
      }

      public int getDimensions() {
         return this.dimensions;
      }
   }
}
