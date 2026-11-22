package org.mtr.mapping.mapper;

import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.tool.DummyClass;

public final class MinecraftServerHelper extends DummyClass {
   @MappedMethod
   public static void iterateWorlds(MinecraftServer minecraftServer, Consumer<ServerWorld> consumer) {
      ((net.minecraft.server.MinecraftServer)minecraftServer.data).getAllLevels().forEach(serverWorld -> consumer.accept(new ServerWorld(serverWorld)));
   }

   @MappedMethod
   public static void iteratePlayers(MinecraftServer minecraftServer, Consumer<ServerPlayerEntity> consumer) {
      ((net.minecraft.server.MinecraftServer)minecraftServer.data)
         .getPlayerList()
         .getPlayers()
         .forEach(serverPlayerEntity -> consumer.accept(new ServerPlayerEntity(serverPlayerEntity)));
   }

   @MappedMethod
   public static void iteratePlayers(ServerWorld serverWorld, Consumer<ServerPlayerEntity> consumer) {
      ((ServerLevel)serverWorld.data).players().forEach(serverPlayerEntity -> consumer.accept(new ServerPlayerEntity(serverPlayerEntity)));
   }

   @MappedMethod
   public static void iteratePlayers(ServerWorld serverWorld, Predicate<ServerPlayerEntity> predicate, Consumer<ServerPlayerEntity> consumer) {
      ((ServerLevel)serverWorld.data)
         .getPlayers(serverPlayerEntity -> predicate.test(new ServerPlayerEntity(serverPlayerEntity)))
         .forEach(serverPlayerEntity -> consumer.accept(new ServerPlayerEntity(serverPlayerEntity)));
   }

   @MappedMethod
   public static Identifier getWorldId(World world) {
      return new Identifier(((Level)world.data).dimension().identifier());
   }
}
