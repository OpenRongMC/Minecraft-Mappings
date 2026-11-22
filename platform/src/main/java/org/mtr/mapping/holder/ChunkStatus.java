package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ChunkStatus extends HolderBase<net.minecraft.world.level.chunk.status.ChunkStatus> {
   public ChunkStatus(net.minecraft.world.level.chunk.status.ChunkStatus data) {
      super(data);
   }

}
