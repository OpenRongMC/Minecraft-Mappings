package org.mtr.mapping.mapper;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.player.Player;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.PlayerInventory;
import org.mtr.mapping.tool.DummyClass;

public final class PlayerHelper extends DummyClass {
   @Nullable
   @MappedMethod
   public static PlayerInventory getPlayerInventory(@Nullable PlayerEntity playerEntity) {
      return playerEntity == null ? null : new PlayerInventory(((Player)playerEntity.data).getInventory());
   }

   @MappedMethod
   public static boolean isHolding(@Nullable PlayerEntity playerEntity, Predicate<Item> predicate) {
      return playerEntity != null && ((Player)playerEntity.data).isHolding(itemStack -> predicate.test(new Item(itemStack.getItem())));
   }
}
