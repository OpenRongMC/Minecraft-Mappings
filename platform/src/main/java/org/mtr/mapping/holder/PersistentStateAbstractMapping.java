package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.saveddata.SavedData;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class PersistentStateAbstractMapping extends SavedData {
   @MappedMethod
   public void setDirty2() {
      super.setDirty();
   }

   @Deprecated
   public final void setDirty() {
      this.setDirty2();
   }

   @MappedMethod
   public void setDirty2(boolean bl) {
      super.setDirty(bl);
   }

   @Deprecated
   public final void setDirty(boolean bl) {
      this.setDirty2(bl);
   }

   @MappedMethod
   public void markDirty2() {
      this.setDirty2();
   }

   @Nonnull
   @MappedMethod
   public abstract CompoundTag writeNbt2(CompoundTag var1);
}
