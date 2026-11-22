package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.item.TooltipFlag;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class TooltipContext extends HolderBase<net.minecraft.world.item.Item.TooltipContext> {
   public TooltipContext(net.minecraft.world.item.Item.TooltipContext data) {
      super(data);
   }

   public TooltipContext(TooltipFlag flag) {
      super(net.minecraft.world.item.Item.TooltipContext.EMPTY);
   }

}
