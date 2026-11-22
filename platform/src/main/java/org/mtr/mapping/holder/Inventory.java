package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.Container;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Inventory extends HolderBase<Container> {
   public Inventory(Container data) {
      super(data);
   }

}
