package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.multiplayer.ClientChunkCache;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ClientChunkManager extends HolderBase<ClientChunkCache> {
   public ClientChunkManager(ClientChunkCache data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public WorldChunk getChunkNow(int i, int j) {
      return new WorldChunk(((ClientChunkCache)this.data).getChunkNow(i, j));
   }


}
