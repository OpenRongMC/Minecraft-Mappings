package org.mtr.mapping.holder;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.StringRepresentable;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class EnumProperty<T extends Enum<T> & StringRepresentable> extends HolderBase<net.minecraft.world.level.block.state.properties.EnumProperty<T>> {
   public EnumProperty(net.minecraft.world.level.block.state.properties.EnumProperty<T> data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String string, Class<T> class_, Predicate<T> predicate) {
      return new EnumProperty<>(net.minecraft.world.level.block.state.properties.EnumProperty.create(string, class_, predicate));
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String string, Class<T> class_, T[] enums) {
      return new EnumProperty<>(net.minecraft.world.level.block.state.properties.EnumProperty.create(string, class_, enums));
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String string, Class<T> class_, List<T> list) {
      return new EnumProperty<>(net.minecraft.world.level.block.state.properties.EnumProperty.create(string, class_, list));
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String string, Class<T> class_) {
      return new EnumProperty<>(net.minecraft.world.level.block.state.properties.EnumProperty.create(string, class_));
   }

   @Nonnull
   @MappedMethod
   public List<T> getPossibleValues() {
      return ((net.minecraft.world.level.block.state.properties.EnumProperty)this.data).getPossibleValues();
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(String name, Class<T> type) {
      return create(name, type);
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(String name, Class<T> type, Predicate<T> filter) {
      return create(name, type, filter);
   }

   @Nonnull
   @MappedMethod
   public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(String name, Class<T> type, T... values) {
      return create(name, type, values);
   }

   @Nonnull
   @MappedMethod
   public Collection<T> getValues() {
      return this.getPossibleValues();
   }
}
