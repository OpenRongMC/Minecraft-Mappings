package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ModelPart extends HolderBase<net.minecraft.client.model.geom.ModelPart> {
   public ModelPart(net.minecraft.client.model.geom.ModelPart data) {
      super(data);
   }


}
