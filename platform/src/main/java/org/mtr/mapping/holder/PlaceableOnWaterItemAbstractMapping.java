package org.mtr.mapping.holder;
import net.minecraft.world.item.Item.Properties;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class PlaceableOnWaterItemAbstractMapping extends PlaceOnWaterBlockItem {

@MappedMethod
   public PlaceableOnWaterItemAbstractMapping(Block block, ItemSettings properties) {
      super((net.minecraft.world.level.block.Block)block.data, (Properties)properties.data);
   }
}
