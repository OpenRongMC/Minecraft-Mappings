package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.DummyInterface;

@ParametersAreNonnullByDefault
public interface ItemColorProvider extends DummyInterface {
   @MappedMethod
   int getColor2(ItemStack var1, int var2);
}
