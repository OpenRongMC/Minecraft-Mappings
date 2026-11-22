package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Items extends HolderBase<net.minecraft.world.item.Items> {
   public Items(net.minecraft.world.item.Items data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static Item getGlassBottleMapped() {
      return new Item(net.minecraft.world.item.Items.GLASS_BOTTLE);
   }

   @Nonnull
   @MappedMethod
   public static Item getIronIngotMapped() {
      return new Item(net.minecraft.world.item.Items.IRON_INGOT);
   }

   @Nonnull
   @MappedMethod
   public static Item getWaterBucketMapped() {
      return new Item(net.minecraft.world.item.Items.WATER_BUCKET);
   }

   @Nonnull
   @MappedMethod
   public static Item getBrushMapped() {
      return new Item(net.minecraft.world.item.Items.BRUSH);
   }

   @Nonnull
   @MappedMethod
   public static Item getShearsMapped() {
      return new Item(net.minecraft.world.item.Items.SHEARS);
   }

   @Nonnull
   @MappedMethod
   public static Item getEmeraldMapped() {
      return new Item(net.minecraft.world.item.Items.EMERALD);
   }

   @Nonnull
   @MappedMethod
   public static Item getBucketMapped() {
      return new Item(net.minecraft.world.item.Items.BUCKET);
   }

   @Nonnull
   @MappedMethod
   public static Item getPotionMapped() {
      return new Item(net.minecraft.world.item.Items.POTION);
   }


}
