package org.mtr.mapping.holder;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ClientPlayNetworkHandler extends HolderBase<ClientPacketListener> {
   public ClientPlayNetworkHandler(ClientPacketListener data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public PlayerListEntry getPlayerInfo(UUID uUID) {
      return new PlayerListEntry(((ClientPacketListener)this.data).getPlayerInfo(uUID));
   }

   @Nonnull
   @MappedMethod
   public PlayerListEntry getPlayerInfo(String string) {
      return new PlayerListEntry(((ClientPacketListener)this.data).getPlayerInfo(string));
   }

   @Nullable
   @MappedMethod
   public PlayerListEntry getPlayerListEntry(UUID uuid) {
      return this.getPlayerInfo(uuid);
   }

   @Nullable
   @MappedMethod
   public PlayerListEntry getPlayerListEntry(String name) {
      return this.getPlayerInfo(name);
   }


}
