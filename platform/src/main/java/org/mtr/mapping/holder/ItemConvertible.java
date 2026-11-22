package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.ItemLike;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ItemConvertible extends HolderBase<ItemLike> {
   public ItemConvertible(ItemLike data) {
      super(data);
   }

   @MappedMethod
   public static ItemConvertible cast(HolderBase<?> data) {
      return new ItemConvertible((ItemLike)data.data);
   }

}
