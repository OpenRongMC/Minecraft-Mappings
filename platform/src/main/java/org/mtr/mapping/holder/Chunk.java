package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Chunk extends HolderBase<ChunkAccess> {
   public Chunk(ChunkAccess data) {
      super(data);
   }

}
