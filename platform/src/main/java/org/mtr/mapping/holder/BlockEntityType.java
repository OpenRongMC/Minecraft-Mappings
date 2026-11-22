package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockEntityType<T extends net.minecraft.world.level.block.entity.BlockEntity>
   extends HolderBase<net.minecraft.world.level.block.entity.BlockEntityType<T>> {
   public BlockEntityType(net.minecraft.world.level.block.entity.BlockEntityType<T> data) {
      super(data);
   }

}
