package org.mtr.mapping.holder;

import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Property<T extends Comparable<T>> extends HolderBase<net.minecraft.world.level.block.state.properties.Property<T>> {
   public Property(net.minecraft.world.level.block.state.properties.Property<T> data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public List<T> getPossibleValues() {
      return ((net.minecraft.world.level.block.state.properties.Property)this.data).getPossibleValues();
   }

   @Nonnull
   @MappedMethod
   public Collection<T> getValues() {
      return this.getPossibleValues();
   }
}
