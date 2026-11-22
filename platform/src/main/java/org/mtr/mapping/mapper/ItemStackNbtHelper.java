package org.mtr.mapping.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.CompoundTag;
import org.mtr.mapping.holder.ItemStack;

public final class ItemStackNbtHelper {
   private static final Field CUSTOM_DATA_TAG;

   private ItemStackNbtHelper() {
   }

   @MappedMethod
   public static CompoundTag getOrCreateTag(ItemStack stack) {
      CustomData customData = (CustomData)((net.minecraft.world.item.ItemStack)stack.data).get(DataComponents.CUSTOM_DATA);
      if (customData == null || customData.isEmpty()) {
         net.minecraft.nbt.CompoundTag fresh = new net.minecraft.nbt.CompoundTag();
         ((net.minecraft.world.item.ItemStack)stack.data).set(DataComponents.CUSTOM_DATA, CustomData.of(fresh));
         customData = (CustomData)((net.minecraft.world.item.ItemStack)stack.data).get(DataComponents.CUSTOM_DATA);
      }

      try {
         return new CompoundTag((net.minecraft.nbt.CompoundTag)CUSTOM_DATA_TAG.get(customData));
      } catch (IllegalAccessException var3) {
         throw new RuntimeException(var3);
      }
   }

   @MappedMethod
   public static CompoundTag getTag(ItemStack stack) {
      CustomData customData = (CustomData)((net.minecraft.world.item.ItemStack)stack.data).get(DataComponents.CUSTOM_DATA);
      if (customData != null && !customData.isEmpty()) {
         try {
            return new CompoundTag(((net.minecraft.nbt.CompoundTag)CUSTOM_DATA_TAG.get(customData)).copy());
         } catch (IllegalAccessException var3) {
            throw new RuntimeException(var3);
         }
      } else {
         return new CompoundTag(new net.minecraft.nbt.CompoundTag());
      }
   }

   @MappedMethod
   public static void saveTag(ItemStack stack, CompoundTag tag) {
      if (((net.minecraft.nbt.CompoundTag)tag.data).isEmpty()) {
         ((net.minecraft.world.item.ItemStack)stack.data).remove(DataComponents.CUSTOM_DATA);
      } else {
         ((net.minecraft.world.item.ItemStack)stack.data).set(DataComponents.CUSTOM_DATA, CustomData.of((net.minecraft.nbt.CompoundTag)tag.data));
      }
   }

   static {
      Field found = null;

      for (Field field : CustomData.class.getDeclaredFields()) {
         if (!Modifier.isStatic(field.getModifiers()) && field.getType() == net.minecraft.nbt.CompoundTag.class) {
            field.setAccessible(true);
            found = field;
            break;
         }
      }

      if (found == null) {
         throw new ExceptionInInitializerError("CustomData has no CompoundTag instance field");
      } else {
         CUSTOM_DATA_TAG = found;
      }
   }
}
