package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class PlayerInventory extends HolderBase<net.minecraft.world.entity.player.Inventory> {
   public PlayerInventory(net.minecraft.world.entity.player.Inventory data) {
      super(data);
   }

   @MappedMethod
   public int countItem(Item item) {
      return ((net.minecraft.world.entity.player.Inventory)this.data).countItem((net.minecraft.world.item.Item)item.data);
   }

   @MappedMethod
   public boolean add(int i, ItemStack itemStack) {
      return ((net.minecraft.world.entity.player.Inventory)this.data).add(i, (net.minecraft.world.item.ItemStack)itemStack.data);
   }

   @MappedMethod
   public boolean add(ItemStack itemStack) {
      return ((net.minecraft.world.entity.player.Inventory)this.data).add((net.minecraft.world.item.ItemStack)itemStack.data);
   }

   @MappedMethod
   public int count(Item item) {
      return this.countItem(item);
   }

   @MappedMethod
   public boolean insertStack(ItemStack stack) {
      return this.add(stack);
   }


}
