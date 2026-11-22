package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class DoorBlock extends HolderBase<net.minecraft.world.level.block.DoorBlock> {
   public DoorBlock(net.minecraft.world.level.block.DoorBlock data) {
      super(data);
   }

}
