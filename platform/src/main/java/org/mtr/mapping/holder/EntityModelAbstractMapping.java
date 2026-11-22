package org.mtr.mapping.holder;
import java.util.function.Function;
import net.minecraft.client.renderer.rendertype.RenderType;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class EntityModelAbstractMapping<T extends EntityRenderState> extends net.minecraft.client.model.EntityModel<T> {

@MappedMethod
   public EntityModelAbstractMapping(ModelPart modelPart) {
      super((net.minecraft.client.model.geom.ModelPart)modelPart.data);
   }

@MappedMethod
   public EntityModelAbstractMapping(ModelPart modelPart, Function<net.minecraft.resources.Identifier, RenderType> function) {
      super((net.minecraft.client.model.geom.ModelPart)modelPart.data, function);
   }
}
