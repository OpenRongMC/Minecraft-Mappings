package org.mtr.mapping.render.shader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.apache.commons.io.IOUtils;
import org.mtr.mapping.holder.ResourceManager;
import org.mtr.mapping.render.tool.GlStateTracker;
import org.mtr.mapping.tool.DummyClass;

public final class PatchingResourceProvider implements ResourceProvider {
   private final ResourceProvider resourceFactory;

   public PatchingResourceProvider(ResourceManager resourceManager) {
      this.resourceFactory = (ResourceProvider)resourceManager.data;
   }

   public Optional<Resource> getResource(Identifier identifier) {
      Identifier newIdentifier = identifier.getPath().contains("_modelmat")
         ? Identifier.fromNamespaceAndPath(identifier.getNamespace(), identifier.getPath().replace("_modelmat", ""))
         : identifier;
      Optional<Resource> resource = this.resourceFactory.getResource(newIdentifier);
      if (resource.isEmpty()) {
         return Optional.empty();
      } else {
         try {
            InputStream inputStream = resource.get().open();
            String returningContent;
            if (newIdentifier.getPath().endsWith(".json")) {
               JsonObject dataObject = JsonParser.parseString(IOUtils.toString(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
               inputStream.close();
               dataObject.addProperty("vertex", dataObject.get("vertex").getAsString() + "_modelmat");
               JsonArray attributeArray = dataObject.get("attributes").getAsJsonArray();

               for (int dummyIndex = 0; attributeArray.size() < 6; dummyIndex++) {
                  attributeArray.add("Dummy" + dummyIndex);
               }

               attributeArray.add("ModelMat");
               returningContent = dataObject.toString();
            } else {
               if (!newIdentifier.getPath().endsWith(".vsh")) {
                  return resource;
               }

               returningContent = patchVertexShaderSource(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
               inputStream.close();
            }

            return Optional.of(new Resource(resource.get().source(), () -> new ByteArrayInputStream(returningContent.getBytes(StandardCharsets.UTF_8))));
         } catch (Exception var9) {
            DummyClass.logException(var9);
            return Optional.empty();
         }
      }
   }

   private static String patchVertexShaderSource(String sourceContent) {
      String[] contentParts = sourceContent.split("void main");
      contentParts[0] = contentParts[0].replace("uniform mat4 ModelViewMat;", "uniform mat4 ModelViewMat;\nin mat4 ModelMat;");
      if (GlStateTracker.isGl4ES()) {
         contentParts[0] = contentParts[0].replace("ivec2", "vec2");
      }

      contentParts[1] = contentParts[1]
         .replaceAll("\\bPosition\\b", "(MODELVIEWMAT * ModelMat * vec4(Position, 1.0)).xyz")
         .replaceAll("\\bNormal\\b", "normalize(mat3(MODELVIEWMAT * ModelMat) * Normal)")
         .replace("ModelViewMat", "mat4(1.0)")
         .replace("MODELVIEWMAT", "ModelViewMat");
      return contentParts[0] + "void main" + contentParts[1];
   }
}
