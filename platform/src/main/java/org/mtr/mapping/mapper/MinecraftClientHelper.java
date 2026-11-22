package org.mtr.mapping.mapper;

import java.io.File;
import java.util.function.Consumer;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.ClientLevel;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ClientWorld;
import org.mtr.mapping.holder.Entity;
import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.tool.DummyClass;

public final class MinecraftClientHelper extends DummyClass {
   @MappedMethod
   public static int getRenderDistance() {
      return ((Options)MinecraftClient.getInstance().getOptionsMapped().data).getEffectiveRenderDistance();
   }

   @MappedMethod
   public static File getResourcePackDirectory() {
      return MinecraftClient.getInstance().getResourcePackDirectory().toFile();
   }

   @MappedMethod
   public static void getEntities(Consumer<Entity> consumer) {
      ClientWorld clientWorld = MinecraftClient.getInstance().getLevelMapped();
      if (clientWorld != null && clientWorld.data != null) {
         ((ClientLevel)clientWorld.data).entitiesForRendering().forEach(entity -> consumer.accept(new Entity(entity)));
      }
   }

   @MappedMethod
   public static void addEntity(EntityAbstractMapping entity) {
      ClientWorld clientWorld = MinecraftClient.getInstance().getLevelMapped();
      if (clientWorld != null && clientWorld.data != null) {
         ((ClientLevel)clientWorld.data).addEntity(entity);
      }
   }
}
