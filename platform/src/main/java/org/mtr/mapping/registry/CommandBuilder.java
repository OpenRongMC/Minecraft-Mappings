package org.mtr.mapping.registry;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import javax.annotation.Nullable;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.tool.DummyClass;

public final class CommandBuilder<T extends ArgumentBuilder<CommandSourceStack, T>> extends DummyClass {
   T argumentBuilder;

   CommandBuilder(T argumentBuilder) {
      this.argumentBuilder = argumentBuilder;
   }

   @MappedMethod
   public void permissionLevel(int permissionLevel) {
      this.argumentBuilder = (T)this.argumentBuilder.requires(Commands.hasPermission(switch (permissionLevel) {
         case 0 -> Commands.LEVEL_ALL;
         case 1 -> Commands.LEVEL_MODERATORS;
         case 2 -> Commands.LEVEL_GAMEMASTERS;
         case 3 -> Commands.LEVEL_ADMINS;
         default -> Commands.LEVEL_OWNERS;
      }));
   }

   @MappedMethod
   public <U> void then(String argumentName, ArgumentType<U> argumentType, Consumer<CommandBuilder<?>> consumer) {
      CommandBuilder<RequiredArgumentBuilder<CommandSourceStack, U>> commandBuilder = new CommandBuilder((T)Commands.argument(argumentName, argumentType));
      consumer.accept(commandBuilder);
      this.argumentBuilder = (T)this.argumentBuilder.then(commandBuilder.argumentBuilder);
   }

   @MappedMethod
   public void then(String commandName, Consumer<CommandBuilder<?>> consumer) {
      CommandBuilder<LiteralArgumentBuilder<CommandSourceStack>> commandBuilder = new CommandBuilder((T)Commands.literal(commandName));
      consumer.accept(commandBuilder);
      this.argumentBuilder = (T)this.argumentBuilder.then(commandBuilder.argumentBuilder);
   }

   @MappedMethod
   public void executes(ToIntFunction<CommandBuilder.ContextHandler> execute) {
      this.argumentBuilder = (T)this.argumentBuilder.executes(context -> execute.applyAsInt(new CommandBuilder.ContextHandler(context)));
   }

   public static class ContextHandler {
      private final CommandContext<CommandSourceStack> context;

      private ContextHandler(CommandContext<CommandSourceStack> context) {
         this.context = context;
      }

      @MappedMethod
      public boolean getBoolean(String argumentName) {
         return BoolArgumentType.getBool(this.context, argumentName);
      }

      @MappedMethod
      public double getDouble(String argumentName) {
         return DoubleArgumentType.getDouble(this.context, argumentName);
      }

      @MappedMethod
      public float getFloat(String argumentName) {
         return FloatArgumentType.getFloat(this.context, argumentName);
      }

      @MappedMethod
      public int getInteger(String argumentName) {
         return IntegerArgumentType.getInteger(this.context, argumentName);
      }

      @MappedMethod
      public long getLong(String argumentName) {
         return LongArgumentType.getLong(this.context, argumentName);
      }

      @MappedMethod
      public String getString(String argumentName) {
         return StringArgumentType.getString(this.context, argumentName);
      }

      @MappedMethod
      public void sendSuccess(String message, boolean broadcastToOps, Object... translatableArguments) {
         ((CommandSourceStack)this.context.getSource()).sendSuccess(() -> Component.translatable(message, translatableArguments), broadcastToOps);
      }

      @MappedMethod
      public void sendFailure(String message, Object... translatableArguments) {
         ((CommandSourceStack)this.context.getSource()).sendFailure(Component.translatable(message, translatableArguments));
      }

      @MappedMethod
      public MinecraftServer getServer() {
         return new MinecraftServer(((CommandSourceStack)this.context.getSource()).getServer());
      }

      @MappedMethod
      public World getWorld() {
         return new World(((CommandSourceStack)this.context.getSource()).getLevel());
      }

      @MappedMethod
      @Nullable
      public ServerPlayerEntity getServerPlayer() {
         ServerPlayer serverPlayerEntity = ((CommandSourceStack)this.context.getSource()).getPlayer();
         return serverPlayerEntity == null ? null : new ServerPlayerEntity(serverPlayerEntity);
      }
   }
}
