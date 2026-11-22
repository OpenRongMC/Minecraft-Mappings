package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.LevelAccessor;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class WorldAccess extends HolderBase<LevelAccessor> {
   public WorldAccess(LevelAccessor data) {
      super(data);
   }

   @MappedMethod
   public static WorldAccess cast(HolderBase<?> data) {
      return new WorldAccess((LevelAccessor)data.data);
   }

   @Nonnull
   @MappedMethod
   public BlockState getBlockState(BlockPos blockPos) {
      return new BlockState(((LevelAccessor)this.data).getBlockState((net.minecraft.core.BlockPos)blockPos.data));
   }

}
