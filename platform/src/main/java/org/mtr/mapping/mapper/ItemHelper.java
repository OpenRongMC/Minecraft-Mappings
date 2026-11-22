package org.mtr.mapping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.TooltipContext;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.tool.DummyInterface;

public interface ItemHelper extends DummyInterface {
   @MappedMethod
   default void addTooltips(ItemStack stack, @Nullable World world, List<MutableText> tooltip, TooltipContext options) {
   }

   @Deprecated
   default void appendTooltipHelper(ItemStack stack, @Nullable World world, Consumer<Component> tooltip, TooltipContext options) {
      List<MutableText> newTooltipList = new ArrayList<>();
      this.addTooltips(stack, world, newTooltipList, options);
      newTooltipList.forEach(mutableText -> tooltip.accept((Component)mutableText.data));
   }
}
