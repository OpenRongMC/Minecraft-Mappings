package org.mtr.mapping.mapper;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ClientWorld;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.tool.DummyClass;

public final class WorldHelper extends DummyClass {
   @MappedMethod
   public static long getTimeOfDay(ServerWorld serverWorld) {
      return ((ServerLevel)serverWorld.data).getOverworldClockTime();
   }

   @MappedMethod
   public static long getTimeOfDay(ClientWorld clientWorld) {
      return ((ClientLevel)clientWorld.data).getOverworldClockTime();
   }

   @MappedMethod
   public static long getTimeOfDay(World world) {
      return ((Level)world.data).getOverworldClockTime();
   }
}
