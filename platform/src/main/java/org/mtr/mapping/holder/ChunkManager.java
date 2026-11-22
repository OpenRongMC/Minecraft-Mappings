package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.chunk.ChunkSource;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ChunkManager extends HolderBase<ChunkSource> {
   public ChunkManager(ChunkSource data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public WorldChunk getChunkNow(int i, int j) {
      return new WorldChunk(((ChunkSource)this.data).getChunkNow(i, j));
   }

   @Nonnull
   @MappedMethod
   public WorldChunk getChunk(int i, int j, boolean bl) {
      return new WorldChunk(((ChunkSource)this.data).getChunk(i, j, bl));
   }

   @Nonnull
   @MappedMethod
   public Chunk getChunk(int i, int j, ChunkStatus chunkStatus, boolean bl) {
      return new Chunk(((ChunkSource)this.data).getChunk(i, j, (net.minecraft.world.level.chunk.status.ChunkStatus)chunkStatus.data, bl));
   }

   @MappedMethod
   public boolean hasChunk(int i, int j) {
      return ((ChunkSource)this.data).hasChunk(i, j);
   }

   @Nonnull
   @MappedMethod
   public WorldChunk getWorldChunk(int x, int z) {
      return this.getChunkNow(x, z);
   }

   @Nonnull
   @MappedMethod
   public WorldChunk getWorldChunk(int x, int z, boolean create) {
      return this.getChunk(x, z, create);
   }

   @MappedMethod
   public boolean isChunkLoaded(int chunkX, int chunkZ) {
      return this.hasChunk(chunkX, chunkZ);
   }
}
