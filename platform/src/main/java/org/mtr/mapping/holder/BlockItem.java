package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockItem extends HolderBase<net.minecraft.world.item.BlockItem> {
   public BlockItem(net.minecraft.world.item.BlockItem data) {
      super(data);
   }


}
