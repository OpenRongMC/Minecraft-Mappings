package org.mtr.mapping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.Entity;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.tool.DummyClass;

public final class EntityHelper extends DummyClass {
   public static final List<UUID> HIDDEN_PLAYERS = new ArrayList<>();

   @MappedMethod
   public static float getPitch(Entity entity) {
      return ((net.minecraft.world.entity.Entity)entity.data).getXRot();
   }

   @MappedMethod
   public static float getYaw(Entity entity) {
      return ((net.minecraft.world.entity.Entity)entity.data).getYRot();
   }

   @MappedMethod
   public static void setPitch(Entity entity, float pitch) {
      ((net.minecraft.world.entity.Entity)entity.data).setXRot(pitch);
   }

   @MappedMethod
   public static void setYaw(Entity entity, float yaw) {
      ((net.minecraft.world.entity.Entity)entity.data).setYRot(yaw);
   }

   @MappedMethod
   public static void spawnItem(ServerWorld serverWorld, BlockPos blockPos, ItemStack itemStack) {
      ((ServerLevel)serverWorld.data)
         .addFreshEntity(
            new ItemEntity(
               (Level)serverWorld.data, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, (net.minecraft.world.item.ItemStack)itemStack.data
            )
         );
   }
}
