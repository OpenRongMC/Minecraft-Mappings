package org.mtr.mapping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.ModelAbstractMapping;
import org.mtr.mapping.holder.RenderLayer;

public abstract class ModelExtension extends ModelAbstractMapping<Object> implements ModelHelper {
   private final int textureWidth;
   private final int textureHeight;
   private final MeshDefinition modelData = new MeshDefinition();
   private final PartDefinition modelPartData = this.modelData.getRoot();
   private final List<ModelPartExtension> modelPartExtensions = new ArrayList<>();

   @MappedMethod
   public ModelExtension(Function<Identifier, RenderLayer> layerFactory, int textureWidth, int textureHeight) {
      super(emptyRoot(), identifier -> (RenderType)layerFactory.apply(new Identifier(identifier)).data);
      this.textureWidth = textureWidth;
      this.textureHeight = textureHeight;
   }

   @Deprecated
   @Override
   public void setupAnim2(Object state) {
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

   private static org.mtr.mapping.holder.ModelPart emptyRoot() {
      return new org.mtr.mapping.holder.ModelPart(new ModelPart(List.of(), Map.of()));
   }
}
