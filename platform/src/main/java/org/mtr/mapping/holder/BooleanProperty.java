package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BooleanProperty extends HolderBase<net.minecraft.world.level.block.state.properties.BooleanProperty> {
   public BooleanProperty(net.minecraft.world.level.block.state.properties.BooleanProperty data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static BooleanProperty create(String string) {
      return new BooleanProperty(net.minecraft.world.level.block.state.properties.BooleanProperty.create(string));
   }

   @Nonnull
   @MappedMethod
   public static BooleanProperty of(String name) {
      return create(name);
   }
}
