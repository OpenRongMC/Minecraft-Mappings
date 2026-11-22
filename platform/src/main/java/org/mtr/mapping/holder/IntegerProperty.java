package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class IntegerProperty extends HolderBase<net.minecraft.world.level.block.state.properties.IntegerProperty> {
   public IntegerProperty(net.minecraft.world.level.block.state.properties.IntegerProperty data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static IntegerProperty create(String string, int i, int j) {
      return new IntegerProperty(net.minecraft.world.level.block.state.properties.IntegerProperty.create(string, i, j));
   }

   @Nonnull
   @MappedMethod
   public static IntegerProperty of(String name, int min, int max) {
      return create(name, min, max);
   }

   
}
