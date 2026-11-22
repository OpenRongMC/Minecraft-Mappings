package org.mtr.mapping.holder;
import net.minecraft.world.level.ItemLike;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.mapper.ItemStackNbtHelper;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ItemStack extends HolderBase<net.minecraft.world.item.ItemStack> {
   public ItemStack(net.minecraft.world.item.ItemStack data) {
      super(data);
   }

   @MappedMethod
   public void shrink(int i) {
      ((net.minecraft.world.item.ItemStack)this.data).shrink(i);
   }

   @Nonnull
   @MappedMethod
   public Item getItem() {
      return new Item(((net.minecraft.world.item.ItemStack)this.data).getItem());
   }

   @Nonnull
   @MappedMethod
   public <T> T set(TypedDataComponent<T> typedDataComponent) {
      return (T)((net.minecraft.world.item.ItemStack)this.data).set(typedDataComponent);
   }

   @Nonnull
   @MappedMethod
   public <T> T set(DataComponentType<T> dataComponentType, T object) {
      return (T)((net.minecraft.world.item.ItemStack)this.data).set(dataComponentType, object);
   }

   @MappedMethod
   public boolean isEmpty() {
      return ((net.minecraft.world.item.ItemStack)this.data).isEmpty();
   }

   @Nonnull
   @MappedMethod
   public static ItemStack getEmptyMapped() {
      return new ItemStack(net.minecraft.world.item.ItemStack.EMPTY);
   }

   @Nonnull
   @MappedMethod
   public ItemStack setCustomName(Text name) {
      this.set(DataComponents.CUSTOM_NAME, (Component)name.data);
      return this;
   }

   @Nonnull
   @MappedMethod
   public CompoundTag getOrCreateTag() {
      return ItemStackNbtHelper.getOrCreateTag(this);
   }

   @MappedMethod
   public void decrement(int count) {
      this.shrink(count);
   }

@MappedMethod
   public ItemStack(Holder<net.minecraft.world.item.Item> holder, int i) {
      super(new net.minecraft.world.item.ItemStack(holder, i));
   }



@MappedMethod
   public ItemStack(Holder<net.minecraft.world.item.Item> holder) {
      super(new net.minecraft.world.item.ItemStack(holder));
   }

@MappedMethod
   public ItemStack(ItemConvertible itemLike) {
      super(new net.minecraft.world.item.ItemStack((ItemLike)itemLike.data));
   }

@MappedMethod
   public ItemStack(ItemConvertible itemLike, int i) {
      super(new net.minecraft.world.item.ItemStack((ItemLike)itemLike.data, i));
   }
}
