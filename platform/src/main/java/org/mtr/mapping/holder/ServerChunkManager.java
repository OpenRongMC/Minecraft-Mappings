package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.server.level.ServerChunkCache;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ServerChunkManager extends HolderBase<ServerChunkCache> {
   public ServerChunkManager(ServerChunkCache data) {
      super(data);
   }


}
