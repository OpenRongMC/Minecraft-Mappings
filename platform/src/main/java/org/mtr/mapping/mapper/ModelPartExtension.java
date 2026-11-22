package org.mtr.mapping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.DummyClass;

public final class ModelPartExtension extends DummyClass {
   ModelPart modelPart;
   private float tempPivotX;
   private float tempPivotY;
   private float tempPivotZ;
   private float tempRotationX;
   private float tempRotationY;
   private float tempRotationZ;
   private int tempU;
   private int tempV;
   private PartDefinition modelPartData;
   private boolean childAdded = false;
   private final List<String> nameTree = new ArrayList<>();

   ModelPartExtension(PartDefinition modelPartData) {
      this.nameTree.add(getRandomPartName());
      this.modelPartData = modelPartData;
   }

   private ModelPartExtension(String name, PartDefinition modelPartData) {
      this.nameTree.add(name);
      this.modelPartData = modelPartData;
   }

   @MappedMethod
   public void setPivot(float x, float y, float z) {
      this.tempPivotX = x;
      this.tempPivotY = y;
      this.tempPivotZ = z;
   }

   @MappedMethod
   public ModelPartExtension setTextureUVOffset(int textureOffsetU, int textureOffsetV) {
      this.tempU = textureOffsetU;
      this.tempV = textureOffsetV;
      return this;
   }

   @MappedMethod
   public void setRotation(float rotationX, float rotationY, float rotationZ) {
      this.tempRotationX = rotationX;
      this.tempRotationY = rotationY;
      this.tempRotationZ = rotationZ;
   }

   @MappedMethod
   public ModelPartExtension addChild() {
      this.setChild();
      String name = getRandomPartName();
      ModelPartExtension modelPartExtension = new ModelPartExtension(
         name, this.modelPartData.addOrReplaceChild(name, CubeListBuilder.create(), this.getModelTransform())
      );
      modelPartExtension.nameTree.addAll(0, this.nameTree);
      return modelPartExtension;
   }

   @MappedMethod
   public void addCuboid(float x, float y, float z, int sizeX, int sizeY, int sizeZ, float inflation, boolean mirrored) {
      this.setChild();
      String name = getRandomPartName();
      CubeListBuilder modelPartBuilder = CubeListBuilder.create()
         .mirror(mirrored)
         .addBox(name, x, y, z, sizeX, sizeY, sizeZ, new CubeDeformation(inflation), this.tempU, this.tempV);
      this.modelPartData.addOrReplaceChild(name, modelPartBuilder, this.getModelTransform());
   }

   @MappedMethod
   public void setOffset(float x, int y, float z) {
      if (this.modelPart != null) {
         this.modelPart.setPos(x, y, z);
      }
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, float x, float z, float rotateY, int light, int overlay) {
      this.render(graphicsHolder, x, 0.0F, z, rotateY, light, overlay);
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, float x, float y, float z, float rotateY, int light, int overlay) {
      if (this.modelPart != null) {
         this.modelPart.setPos(x, y, z);
         this.modelPart.yRot = rotateY;
         if (graphicsHolder.matrixStack != null && graphicsHolder.vertexConsumer != null) {
            this.modelPart.render(graphicsHolder.matrixStack, graphicsHolder.vertexConsumer, light, overlay);
         }
      }
   }

   @Deprecated
   void setModelPart(ModelPart mainModelPart) {
      this.modelPart = mainModelPart;
      this.nameTree.forEach(name -> this.modelPart = this.modelPart.getChild(name));
   }

   private void setChild() {
      if (!this.childAdded) {
         this.modelPartData = this.modelPartData
            .addOrReplaceChild(this.getLastName(), CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
         this.childAdded = true;
      }
   }

   private String getLastName() {
      return this.nameTree.isEmpty() ? "" : this.nameTree.get(this.nameTree.size() - 1);
   }

   private PartPose getModelTransform() {
      return PartPose.offsetAndRotation(this.tempPivotX, this.tempPivotY, this.tempPivotZ, this.tempRotationX, this.tempRotationY, this.tempRotationZ);
   }

   private static String getRandomPartName() {
      return "part" + Math.abs(new Random().nextLong());
   }
}
