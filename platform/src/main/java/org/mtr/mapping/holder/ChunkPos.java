package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ChunkPos extends HolderBase<net.minecraft.world.level.ChunkPos> {
   public ChunkPos(net.minecraft.world.level.ChunkPos data) {
      super(data);
   }

@MappedMethod
   public ChunkPos(long l) {
      super(net.minecraft.world.level.ChunkPos.unpack(l));
   }

@MappedMethod
   public ChunkPos(BlockPos blockPos) {
      super(net.minecraft.world.level.ChunkPos.containing((net.minecraft.core.BlockPos)blockPos.data));
   }


}
