package org.mtr.mapping.holder;
import java.util.function.Function;
import net.minecraft.client.renderer.rendertype.RenderType;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class ModelAbstractMapping<S> extends net.minecraft.client.model.Model<S> {

   @MappedMethod
   public void setupAnim2(S object) {
      super.setupAnim(object);
   }

   @Deprecated
   public final void setupAnim(S object) {
      this.setupAnim2(object);
   }

@MappedMethod
   public ModelAbstractMapping(ModelPart modelPart, Function<net.minecraft.resources.Identifier, RenderType> function) {
      super((net.minecraft.client.model.geom.ModelPart)modelPart.data, function);
   }
}
