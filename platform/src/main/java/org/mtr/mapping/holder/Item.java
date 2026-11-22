package org.mtr.mapping.holder;
import net.minecraft.world.item.Item.Properties;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Item extends HolderBase<net.minecraft.world.item.Item> {
   public Item(net.minecraft.world.item.Item data) {
      super(data);
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((net.minecraft.world.item.Item)this.data).equals(((HolderBase)data).data);
   }

   @Nonnull
   @MappedMethod
   public final Text getName() {
      return new Text(((net.minecraft.world.item.Item)this.data).getName(((net.minecraft.world.item.Item)this.data).getDefaultInstance()));
   }

   @Nonnull
   @MappedMethod
   public Text getName(ItemStack itemStack) {
      return new Text(((net.minecraft.world.item.Item)this.data).getName((net.minecraft.world.item.ItemStack)itemStack.data));
   }

   @Nonnull
   @MappedMethod
   public final String getDescriptionId() {
      return ((net.minecraft.world.item.Item)this.data).getDescriptionId();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getDefaultInstance() {
      return new ItemStack(((net.minecraft.world.item.Item)this.data).getDefaultInstance());
   }

   @Nonnull
   @MappedMethod
   public ItemStack getDefaultStack() {
      return this.getDefaultInstance();
   }

   @Nonnull
   @MappedMethod
   public String getTranslationKey() {
      return this.getDescriptionId();
   }

@MappedMethod
   public Item(ItemSettings properties) {
      super(new net.minecraft.world.item.Item((Properties)properties.data));
   }
}
