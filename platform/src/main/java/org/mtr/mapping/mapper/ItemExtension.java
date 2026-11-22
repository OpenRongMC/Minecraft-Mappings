package org.mtr.mapping.mapper;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.component.TooltipDisplay;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.ItemAbstractMapping;
import org.mtr.mapping.holder.ItemSettings;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.ItemUsageContext;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.World;

public class ItemExtension extends ItemAbstractMapping implements ItemHelper {
   public ItemExtension(ItemSettings itemSettings) {
      super(itemSettings);
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

   @Deprecated
   @Override
   public void appendHoverText2(ItemStack stack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> tooltip, TooltipFlag flag) {
      super.appendHoverText2(stack, tooltipContext, tooltipDisplay, tooltip, flag);
      this.appendTooltipHelper(stack, null, tooltip, new org.mtr.mapping.holder.TooltipContext(tooltipContext));
   }

   @MappedMethod
   public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
   }

   @MappedMethod
   public void addTooltips(ItemStack stack, @Nullable World world, List<MutableText> tooltip, TooltipContext options) {
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
