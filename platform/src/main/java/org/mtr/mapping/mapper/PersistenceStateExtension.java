package org.mtr.mapping.mapper;

import java.util.function.Supplier;
import javax.annotation.Nonnull;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.CompoundTag;
import org.mtr.mapping.holder.PersistentStateAbstractMapping;
import org.mtr.mapping.holder.ServerWorld;

public abstract class PersistenceStateExtension extends PersistentStateAbstractMapping {
   @MappedMethod
   public PersistenceStateExtension(String key) {
   }

   @MappedMethod
   public abstract void readNbt(CompoundTag var1);

   @Nonnull
   @MappedMethod
   @Override
   public abstract CompoundTag writeNbt2(CompoundTag var1);

   @MappedMethod
   @Override
   public void markDirty2() {
      this.setDirty2();
   }

   @MappedMethod
   public static PersistenceStateExtension register(ServerWorld serverWorld, Supplier<PersistenceStateExtension> supplier, String modId) {
      return supplier.get();
   }
}
