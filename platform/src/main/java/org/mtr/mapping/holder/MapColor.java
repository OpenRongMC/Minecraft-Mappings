package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class MapColor extends HolderBase<net.minecraft.world.level.material.MapColor> {
   public MapColor(net.minecraft.world.level.material.MapColor data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorLightGreenMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_LIGHT_GREEN);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorBlackMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_BLACK);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorCyanMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_CYAN);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getQuartzMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.QUARTZ);
   }

   @MappedMethod
   public int getColMapped() {
      return ((net.minecraft.world.level.material.MapColor)this.data).col;
   }

   @Nonnull
   @MappedMethod
   public static MapColor getWoodMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.WOOD);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getMetalMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.METAL);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getSnowMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.SNOW);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorLightGrayMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_LIGHT_GRAY);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorYellowMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_YELLOW);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorMagentaMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_MAGENTA);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorOrangeMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_ORANGE);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorBlueMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_BLUE);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorLightBlueMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_LIGHT_BLUE);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorGreenMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_GREEN);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorGrayMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_GRAY);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorPurpleMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_PURPLE);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getDiamondMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.DIAMOND);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorRedMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_RED);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorPinkMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_PINK);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getStoneMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.STONE);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getEmeraldMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.EMERALD);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getColorBrownMapped() {
      return new MapColor(net.minecraft.world.level.material.MapColor.COLOR_BROWN);
   }

   @Nonnull
   @MappedMethod
   public static MapColor getOakTanMapped() {
      return getWoodMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getStoneGrayMapped() {
      return getStoneMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getEmeraldGreenMapped() {
      return getEmeraldMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getIronGrayMapped() {
      return getMetalMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getBrownMapped() {
      return getColorBrownMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getPurpleMapped() {
      return getColorPurpleMapped();
   }

   @MappedMethod
   public int getColorMapped() {
      return this.getColMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getWhiteMapped() {
      return getSnowMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getOffWhiteMapped() {
      return getQuartzMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getBlackMapped() {
      return getColorBlackMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getRedMapped() {
      return getColorRedMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getOrangeMapped() {
      return getColorOrangeMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getYellowMapped() {
      return getColorYellowMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getLimeMapped() {
      return getColorLightGreenMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getGreenMapped() {
      return getColorGreenMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getCyanMapped() {
      return getColorCyanMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getLightBlueMapped() {
      return getColorLightBlueMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getBlueMapped() {
      return getColorBlueMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getMagentaMapped() {
      return getColorMagentaMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getPinkMapped() {
      return getColorPinkMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getGrayMapped() {
      return getColorGrayMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getLightGrayMapped() {
      return getColorLightGrayMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getDiamondBlueMapped() {
      return getDiamondMapped();
   }

   @Nonnull
   @MappedMethod
   public static MapColor getPalePurpleMapped() {
      return getColorPurpleMapped();
   }

   
}
