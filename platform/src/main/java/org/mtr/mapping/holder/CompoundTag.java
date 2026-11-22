package org.mtr.mapping.holder;


import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class CompoundTag extends HolderBase<net.minecraft.nbt.CompoundTag> {
   public CompoundTag(net.minecraft.nbt.CompoundTag data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public ListTag getListOrEmpty(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getListOrEmpty(string);
   }

   @MappedMethod
   public void putInt(String string, int i) {
      ((net.minecraft.nbt.CompoundTag)this.data).putInt(string, i);
   }

   @MappedMethod
   public void putBoolean(String string, boolean bl) {
      ((net.minecraft.nbt.CompoundTag)this.data).putBoolean(string, bl);
   }

   @Nonnull
   @MappedMethod
   public int[] getIntArray(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getIntArray(string).orElseGet(() -> new int[0]);
   }

   @Nonnull
   @MappedMethod
   public String getString(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getStringOr(string, "");
   }

   @Nonnull
   @MappedMethod
   public long[] getLongArray(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getLongArray(string).orElseGet(() -> new long[0]);
   }

   @MappedMethod
   public float getFloat(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getFloatOr(string, 0.0F);
   }

   @MappedMethod
   public double getDouble(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getDoubleOr(string, 0.0);
   }

   @Nonnull
   @MappedMethod
   public Tag remove(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).remove(string);
   }

   @MappedMethod
   public void putLong(String string, long l) {
      ((net.minecraft.nbt.CompoundTag)this.data).putLong(string, l);
   }

   @MappedMethod
   public boolean contains(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).contains(string);
   }

   @MappedMethod
   public boolean contains(String string, int type) {
      return ((net.minecraft.nbt.CompoundTag)this.data).contains(string);
   }

   @MappedMethod
   public int getInt(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getIntOr(string, 0);
   }

   @MappedMethod
   public void putFloat(String string, float f) {
      ((net.minecraft.nbt.CompoundTag)this.data).putFloat(string, f);
   }

   @MappedMethod
   public void putLongArray(String string, long[] ls) {
      ((net.minecraft.nbt.CompoundTag)this.data).putLongArray(string, ls);
   }

   @MappedMethod
   public long getLong(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getLongOr(string, 0L);
   }

   @MappedMethod
   public void putString(String string, String string2) {
      ((net.minecraft.nbt.CompoundTag)this.data).putString(string, string2);
   }

   @MappedMethod
   public boolean getBoolean(String string) {
      return ((net.minecraft.nbt.CompoundTag)this.data).getBooleanOr(string, false);
   }

   @MappedMethod
   public void putIntArray(String string, int[] is) {
      ((net.minecraft.nbt.CompoundTag)this.data).putIntArray(string, is);
   }

   @MappedMethod
   public void putDouble(String string, double d) {
      ((net.minecraft.nbt.CompoundTag)this.data).putDouble(string, d);
   }

   @MappedMethod
   public void putLongArray(String key, List<Long> values) {
      long[] arr = new long[values.size()];

      for (int i = 0; i < values.size(); i++) {
         arr[i] = values.get(i);
      }

      this.putLongArray(key, arr);
   }

   @MappedMethod
   public void putIntArray(String key, List<Integer> values) {
      int[] arr = new int[values.size()];

      for (int i = 0; i < values.size(); i++) {
         arr[i] = values.get(i);
      }

      this.putIntArray(key, arr);
   }


}
