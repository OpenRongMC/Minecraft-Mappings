package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.OverlayTexture;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.model.RawMesh;
import org.mtr.mapping.render.model.RawModel;
import org.mtr.mapping.render.obj.AtlasManager;
import org.mtr.mapping.render.obj.ObjModelLoader;

import org.mtr.mapping.render.vertex.CapturingVertexConsumer;
import org.mtr.mapping.render.vertex.VertexAttributeMapping;
import org.mtr.mapping.render.vertex.VertexAttributeSource;
import org.mtr.mapping.render.vertex.VertexAttributeType;
import org.mtr.mapping.tool.DummyClass;

public final class OptimizedModel extends DummyClass {
   List<NativeModelMesh> uploadedParts;
   private static final AtlasManager ATLAS_MANAGER = new AtlasManager();

   @MappedMethod
   public static OptimizedModel fromMaterialGroups(Collection<OptimizedModel.MaterialGroup> materialGroups) {
      CapturingVertexConsumer capturingVertexConsumer = new CapturingVertexConsumer();
      materialGroups.forEach(materialGroup -> {
         capturingVertexConsumer.beginStage(materialGroup.materialProperties);
         materialGroup.modelPartConsumers.forEach(modelPartConsumer -> modelPartConsumer.accept(capturingVertexConsumer));
      });
      capturingVertexConsumer.rawModel.triangulate();
      RawModel rawModel = new RawModel();
      capturingVertexConsumer.rawModel.iterateRawMeshList(rawModel::append);
      rawModel.distinct();
      List<NativeModelMesh> parts = new ArrayList<>();
      rawModel.iterateRawMeshList(mesh -> parts.add(NativeModelMesh.compile(mesh)));
      return new OptimizedModel(parts);
   }

   @MappedMethod
   public static OptimizedModel fromObjModels(Collection<OptimizedModel.ObjModel> objModels) {
      List<NativeModelMesh> uploadedParts = new ArrayList<>();
      objModels.forEach(objModel -> {
         objModel.rawModel.generateNormals();
         objModel.rawModel.distinct();
         objModel.rawModel.iterateRawMeshList(mesh -> uploadedParts.add(NativeModelMesh.compile(mesh)));
      });
      return new OptimizedModel(uploadedParts);
   }

   OptimizedModel(List<NativeModelMesh> uploadedParts) {
      this.uploadedParts = List.copyOf(uploadedParts);
   }

   @MappedMethod
   public OptimizedModel(OptimizedModel... optimizedModels) {
      this.uploadedParts = new ArrayList<>();

      for (OptimizedModel optimizedModel : optimizedModels) {
         this.uploadedParts.addAll(optimizedModel.uploadedParts);
      }
   }

   @MappedMethod
   public void close() {
      this.uploadedParts = List.of();
   }

   public static final class MaterialGroup {
      private final MaterialProperties materialProperties;
      private final List<Consumer<CapturingVertexConsumer>> modelPartConsumers = new ArrayList<>();

      @MappedMethod
      public MaterialGroup(OptimizedModel.ShaderType shaderType, Identifier texture) {
         this.materialProperties = new MaterialProperties(shaderType, texture, null);
      }

      @MappedMethod
      public void addCube(ModelPartExtension modelPart, double x, double y, double z, boolean flipped, int light) {
         if (modelPart.modelPart != null) {
            this.modelPartConsumers.add(capturingVertexConsumer -> {
               PoseStack matrixStack = new PoseStack();
               matrixStack.translate(x, y, z);
               if (flipped) {
                  matrixStack.rotate(Axis.YP.rotationDegrees(180.0F));
               }

               modelPart.modelPart.render(matrixStack, capturingVertexConsumer, light, OverlayTexture.getNoOverlayMapped());
               capturingVertexConsumer.finish();
            });
         }
      }
   }

   public static final class ObjModel {
      private final float minX;
      private final float minY;
      private final float minZ;
      private final float maxX;
      private final float maxY;
      private final float maxZ;
      private final List<RawMesh> rawMeshes;
      private final RawModel rawModel = new RawModel();

      private ObjModel(List<RawMesh> rawMeshes, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
         this.minX = minX;
         this.minY = minY;
         this.minZ = minZ;
         this.maxX = maxX;
         this.maxY = maxY;
         this.maxZ = maxZ;
         this.rawMeshes = rawMeshes;
      }

      @MappedMethod
      public static Map<String, OptimizedModel.ObjModel> loadModel(
         String objString,
         Function<String, String> mtlResolver,
         Function<String, Identifier> textureResolver,
         @Nullable Identifier atlasIndex,
         boolean splitModel,
         boolean flipTextureV
      ) {
         if (atlasIndex != null) {
            OptimizedModel.ATLAS_MANAGER.load(atlasIndex);
         }

         Map<String, OptimizedModel.ObjModel> objModels = new HashMap<>();
         ObjModelLoader.loadModel(objString, mtlResolver, textureResolver, OptimizedModel.ATLAS_MANAGER, splitModel).forEach((key, rawMeshes) -> {
            prepareRawMeshes((List<RawMesh>)rawMeshes, flipTextureV);
            float[] bounds = new float[]{Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE};
            rawMeshes.forEach(rawMesh -> rawMesh.vertices.forEach(vertex -> {
               float x = vertex.position.getX();
               float y = vertex.position.getY();
               float z = vertex.position.getZ();
               bounds[0] = Math.min(bounds[0], x);
               bounds[1] = Math.min(bounds[1], y);
               bounds[2] = Math.min(bounds[2], z);
               bounds[3] = Math.max(bounds[3], x);
               bounds[4] = Math.max(bounds[4], y);
               bounds[5] = Math.max(bounds[5], z);
            }));
            objModels.put(key, new OptimizedModel.ObjModel((List<RawMesh>)rawMeshes, bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]));
         });
         return objModels;
      }

      @MappedMethod
      public static Map<String, List<RawMesh>> loadRawModel(
         String objString,
         Function<String, String> mtlResolver,
         Function<String, Identifier> textureResolver,
         @Nullable Identifier atlasIndex,
         boolean splitModel,
         boolean flipTextureV
      ) {
         if (atlasIndex != null) {
            OptimizedModel.ATLAS_MANAGER.load(atlasIndex);
         }

         Map<String, List<RawMesh>> rawModels = ObjModelLoader.loadModel(objString, mtlResolver, textureResolver, OptimizedModel.ATLAS_MANAGER, splitModel);
         rawModels.values().forEach(rawMeshes -> prepareRawMeshes((List<RawMesh>)rawMeshes, flipTextureV));
         return rawModels;
      }

      @MappedMethod
      public void addTransformation(OptimizedModel.ShaderType shaderType, double x, double y, double z, boolean flipped) {
         this.rawMeshes.forEach(rawMesh -> {
            RawMesh newRawMesh = new RawMesh(shaderType, rawMesh);
            newRawMesh.applyTranslation((float)x, (float)y, (float)z);
            if (flipped) {
               newRawMesh.applyRotation(new Vector3f(0.0F, 1.0F, 0.0F), 180.0F);
            }

            this.rawModel.append(newRawMesh);
         });
      }

      @MappedMethod
      public void applyTranslation(double x, double y, double z) {
         this.rawMeshes.forEach(rawMesh -> rawMesh.applyTranslation((float)x, (float)y, (float)z));
      }

      @MappedMethod
      public void applyRotation(double x, double y, double z) {
         this.rawMeshes.forEach(rawMesh -> {
            rawMesh.applyRotation(new Vector3f(1.0F, 0.0F, 0.0F), (float)x);
            rawMesh.applyRotation(new Vector3f(0.0F, 1.0F, 0.0F), (float)y);
            rawMesh.applyRotation(new Vector3f(0.0F, 0.0F, 1.0F), (float)z);
         });
      }

      @MappedMethod
      public void applyScale(double x, double y, double z) {
         this.rawMeshes.forEach(rawMesh -> rawMesh.applyScale((float)x, (float)y, (float)z));
      }

      @MappedMethod
      public void applyMirror(boolean x, boolean y, boolean z) {
         this.rawMeshes.forEach(rawMesh -> rawMesh.applyMirror(x, y, z, x, y, z));
      }

      @MappedMethod
      public float getMinX() {
         return this.minX;
      }

      @MappedMethod
      public float getMinY() {
         return this.minY;
      }

      @MappedMethod
      public float getMinZ() {
         return this.minZ;
      }

      @MappedMethod
      public float getMaxX() {
         return this.maxX;
      }

      @MappedMethod
      public float getMaxY() {
         return this.maxY;
      }

      @MappedMethod
      public float getMaxZ() {
         return this.maxZ;
      }

      private static void prepareRawMeshes(List<RawMesh> rawMeshes, boolean flipTextureV) {
         rawMeshes.forEach(rawMesh -> {
            rawMesh.applyRotation(new Vector3f(1.0F, 0.0F, 0.0F), 180.0F);
            if (flipTextureV) {
               rawMesh.applyUVMirror(false, true);
            }
         });
      }
   }

   public static enum ShaderType {
      CUTOUT,
      TRANSLUCENT,
      CUTOUT_BRIGHT,
      TRANSLUCENT_BRIGHT,
      CUTOUT_GLOWING,
      TRANSLUCENT_GLOWING;
   }
}
