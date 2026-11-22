package org.mtr.mapping.mapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.DetectedVersion;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import org.apache.commons.io.IOUtils;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.tool.DummyClass;

public final class ResourceManagerHelper extends DummyClass {
   @MappedMethod
   public static void readResource(Identifier identifier, Consumer<InputStream> consumer) {
      try {
         Optional<Resource> optionalResource = MinecraftClient.getInstance().getResourceManager().getResource(identifier);
         optionalResource.ifPresent(resource -> readResource(resource, consumer));
      } catch (Exception var3) {
         logException(var3);
      }
   }

   @MappedMethod
   public static String readResource(Identifier identifier) {
      String[] string = new String[]{""};
      readResource(identifier, inputStream -> {
         try {
            string[0] = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
         } catch (Exception var3) {
            logException(var3);
         }
      });
      return string[0];
   }

   @MappedMethod
   public static void readAllResources(Identifier identifier, Consumer<InputStream> consumer) {
      try {
         MinecraftClient.getInstance().getResourceManager().getResourceStack(identifier).forEach(resource -> readResource(resource, consumer));
      } catch (Exception var3) {
         logException(var3);
      }
   }

   @MappedMethod
   public static void readDirectory(String path, BiConsumer<Identifier, InputStream> consumer) {
      try {
         MinecraftClient.getInstance()
            .getResourceManager()
            .listResources(path, identifier -> true)
            .forEach((identifier, resource) -> readResource(resource, inputStream -> consumer.accept(new Identifier(identifier), inputStream)));
      } catch (Exception var3) {
         logException(var3);
      }
   }

   private static void readResource(Resource resource, Consumer<InputStream> consumer) {
      try (InputStream inputStream = resource.open()) {
         consumer.accept(inputStream);
      } catch (Exception var7) {
         logException(var7);
      }
   }

   @MappedMethod
   public static int getResourcePackVersion() {
      return DetectedVersion.BUILT_IN.packVersion(PackType.CLIENT_RESOURCES).major();
   }

   @MappedMethod
   public static int getDataPackVersion() {
      return DetectedVersion.BUILT_IN.packVersion(PackType.SERVER_DATA).major();
   }
}
