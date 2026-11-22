package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Blocks extends HolderBase<net.minecraft.world.level.block.Blocks> {
   public Blocks(net.minecraft.world.level.block.Blocks data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static Block getDirtMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.DIRT);
   }

   @Nonnull
   @MappedMethod
   public static Block getAirMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.AIR);
   }

   @Nonnull
   @MappedMethod
   public static Block getWhiteConcreteMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.CONCRETE.white());
   }

   @Nonnull
   @MappedMethod
   public static Block getRedstoneBlockMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK);
   }

   @Nonnull
   @MappedMethod
   public static Block getBricksMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.BRICKS);
   }

   @Nonnull
   @MappedMethod
   public static Block getRedstoneWireMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.REDSTONE_WIRE);
   }

   @Nonnull
   @MappedMethod
   public static Block getSmoothStoneMapped() {
      return new Block(net.minecraft.world.level.block.Blocks.SMOOTH_STONE);
   }


}
