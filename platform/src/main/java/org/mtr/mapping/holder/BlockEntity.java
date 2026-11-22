package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.Level;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockEntity extends HolderBase<net.minecraft.world.level.block.entity.BlockEntity> {
   public BlockEntity(net.minecraft.world.level.block.entity.BlockEntity data) {
      super(data);
   }

   @Nullable
   @MappedMethod
   public World getLevel() {
      Level level = ((net.minecraft.world.level.block.entity.BlockEntity)this.data).getLevel();
      return level == null ? null : new World(level);
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos() {
      return new BlockPos(((net.minecraft.world.level.block.entity.BlockEntity)this.data).getBlockPos());
   }

   @Nullable
   @MappedMethod
   public World getWorld() {
      return this.getLevel();
   }

   @Nonnull
   @MappedMethod
   public BlockPos getPos() {
      return this.getBlockPos();
   }

   
}
