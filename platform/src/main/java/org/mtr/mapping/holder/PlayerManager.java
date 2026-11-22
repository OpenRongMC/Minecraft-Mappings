package org.mtr.mapping.holder;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.server.players.PlayerList;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class PlayerManager extends HolderBase<PlayerList> {
   public PlayerManager(PlayerList data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public ServerPlayerEntity getPlayer(UUID uUID) {
      return new ServerPlayerEntity(((PlayerList)this.data).getPlayer(uUID));
   }

   @Nonnull
   @MappedMethod
   public ServerPlayerEntity getPlayer(String string) {
      return new ServerPlayerEntity(((PlayerList)this.data).getPlayer(string));
   }

}
