package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class DirectionProperty extends HolderBase<net.minecraft.world.level.block.state.properties.EnumProperty<net.minecraft.core.Direction>> {
   public DirectionProperty(net.minecraft.world.level.block.state.properties.EnumProperty<net.minecraft.core.Direction> data) {
      super(data);
   }

}
