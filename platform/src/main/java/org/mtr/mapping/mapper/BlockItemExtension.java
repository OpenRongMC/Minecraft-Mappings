package org.mtr.mapping.mapper;

import javax.annotation.Nonnull;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.BlockItemAbstractMapping;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.ItemSettings;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.ItemUsageContext;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.World;

public class BlockItemExtension extends BlockItemAbstractMapping {
   @MappedMethod
   public BlockItemExtension(Block block, ItemSettings itemSettings) {
      super(block, itemSettings);
   }

   @Deprecated
   @Override
   public ActionResult use2(World world, PlayerEntity user, Hand hand) {
      this.useWithoutResult(world, user, hand);
      return super.use2(world, user, hand);
   }

   @Nonnull
   @MappedMethod
   public ActionResult useOnBlock2(ItemUsageContext context) {
      return super.useOn2(context);
   }

   @Nonnull
   @Deprecated
   @Override
   public ActionResult useOn2(ItemUsageContext context) {
      return this.useOnBlock2(context);
   }

   @MappedMethod
   public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
   }

   @MappedMethod
   public boolean hasGlint2(ItemStack stack) {
      return super.isFoil2(stack);
   }

   @Deprecated
   @Override
   public boolean isFoil2(ItemStack stack) {
      return this.hasGlint2(stack);
   }
}
