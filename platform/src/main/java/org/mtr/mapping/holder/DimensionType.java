package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class DimensionType extends HolderBase<net.minecraft.world.level.dimension.DimensionType> {
   public DimensionType(net.minecraft.world.level.dimension.DimensionType data) {
      super(data);
   }


}
