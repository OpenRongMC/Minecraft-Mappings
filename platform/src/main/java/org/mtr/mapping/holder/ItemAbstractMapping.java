package org.mtr.mapping.holder;
import net.minecraft.world.item.Item.Properties;


import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class ItemAbstractMapping extends net.minecraft.world.item.Item {

   @Nonnull
   @MappedMethod
   public ActionResult use2(World level, PlayerEntity player, Hand interactionHand) {
      return new ActionResult(super.use((Level)level.data, (Player)player.data, interactionHand.data));
   }

   @Deprecated
   public final InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
      ActionResult tempData = this.use2(new World(level), new PlayerEntity(player), Hand.convert(interactionHand));
      return tempData == null ? null : (InteractionResult)tempData.data;
   }

   @MappedMethod
   public boolean isFoil2(ItemStack itemStack) {
      return super.isFoil((net.minecraft.world.item.ItemStack)itemStack.data);
   }

   @Deprecated
   public final boolean isFoil(net.minecraft.world.item.ItemStack itemStack) {
      return this.isFoil2(new ItemStack(itemStack));
   }

   @MappedMethod
   public void appendHoverText2(
      ItemStack itemStack,
      net.minecraft.world.item.Item.TooltipContext tooltipContext,
      TooltipDisplay tooltipDisplay,
      Consumer<Component> consumer,
      TooltipFlag tooltipFlag
   ) {
      super.appendHoverText((net.minecraft.world.item.ItemStack)itemStack.data, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
   }

   @Deprecated
   public final void appendHoverText(
      net.minecraft.world.item.ItemStack itemStack,
      net.minecraft.world.item.Item.TooltipContext tooltipContext,
      TooltipDisplay tooltipDisplay,
      Consumer<Component> consumer,
      TooltipFlag tooltipFlag
   ) {
      this.appendHoverText2(new ItemStack(itemStack), tooltipContext, tooltipDisplay, consumer, tooltipFlag);
   }

   @Nonnull
   @MappedMethod
   public ActionResult useOn2(ItemUsageContext useOnContext) {
      return new ActionResult(super.useOn((UseOnContext)useOnContext.data));
   }

   @Deprecated
   public final InteractionResult useOn(UseOnContext useOnContext) {
      ActionResult tempData = this.useOn2(new ItemUsageContext(useOnContext));
      return tempData == null ? null : (InteractionResult)tempData.data;
   }

   @Nonnull
   @MappedMethod
   public String getDescriptionIdMapped() {
      return this.descriptionId;
   }

@MappedMethod
   public ItemAbstractMapping(ItemSettings properties) {
      super((Properties)properties.data);
   }
}
