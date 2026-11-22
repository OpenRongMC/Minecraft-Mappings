package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class PlayerListEntry extends HolderBase<PlayerInfo> {
   public PlayerListEntry(PlayerInfo data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public GameMode getGameMode() {
      return GameMode.convert(((PlayerInfo)this.data).getGameMode());
   }


}
