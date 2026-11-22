package org.mtr.mapping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.EntityModelAbstractMapping;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;

public abstract class EntityModelExtension<T extends EntityAbstractMapping> extends EntityModelAbstractMapping<EntityRenderState> implements ModelHelper {
   private final int textureWidth;
   private final int textureHeight;
   private final MeshDefinition modelData = new MeshDefinition();
   private final PartDefinition modelPartData = this.modelData.getRoot();
   private final List<ModelPartExtension> modelPartExtensions = new ArrayList<>();

   @MappedMethod
   public EntityModelExtension(Function<Identifier, RenderLayer> layerFactory, int textureWidth, int textureHeight) {
      super(emptyRoot(), identifier -> (RenderType)layerFactory.apply(new Identifier(identifier)).data);
      this.textureWidth = textureWidth;
      this.textureHeight = textureHeight;
   }

   @MappedMethod
   public EntityModelExtension(int textureWidth, int textureHeight) {
      super(emptyRoot());
      this.textureWidth = textureWidth;
      this.textureHeight = textureHeight;
   }

   @MappedMethod
   public final ModelPartExtension createModelPart() {
      ModelPartExtension modelPartExtension = new ModelPartExtension(this.modelPartData);
      this.modelPartExtensions.add(modelPartExtension);
      return modelPartExtension;
   }

   @MappedMethod
   public final void buildModel() {
      ModelPart modelPart = LayerDefinition.create(this.modelData, this.textureWidth, this.textureHeight).bakeRoot();
      this.modelPartExtensions.forEach(modelPartExtension -> modelPartExtension.setModelPart(modelPart));
   }

   @MappedMethod
   public void setAngles2(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
   }

   @MappedMethod
   @Override
   public void render(GraphicsHolder graphicsHolder, int light, int overlay, float red, float green, float blue, float alpha) {
   }

   private static org.mtr.mapping.holder.ModelPart emptyRoot() {
      return new org.mtr.mapping.holder.ModelPart(new ModelPart(List.of(), Map.of()));
   }
}
