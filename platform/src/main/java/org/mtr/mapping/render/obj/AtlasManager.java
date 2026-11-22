package org.mtr.mapping.render.obj;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.ResourceManagerHelper;
import org.mtr.mapping.render.model.RawMesh;

public final class AtlasManager {
   private final Map<Identifier, AtlasSprite> sprites = new HashMap<>();
   private final Set<Identifier> noAtlasList = new HashSet<>();

   public void load(Identifier atlasConfiguration) {
      JsonObject atlasConfigurationObject = JsonParser.parseString(ResourceManagerHelper.readResource(atlasConfiguration)).getAsJsonObject();
      String basePath = atlasConfigurationObject.get("basePath").getAsString();
      atlasConfigurationObject.get("sheets")
         .getAsJsonArray()
         .forEach(
            sheetObject -> {
               Identifier sheetConfiguration = ObjModelLoader.resolveRelativePath(atlasConfiguration, sheetObject.getAsString(), ".json");
               Identifier sheetTexture = ObjModelLoader.resolveRelativePath(atlasConfiguration, sheetObject.getAsString(), ".png");
               JsonObject sheetConfigurationObject = JsonParser.parseString(ResourceManagerHelper.readResource(sheetConfiguration)).getAsJsonObject();
               int sheetWidth = sheetConfigurationObject.get("meta").getAsJsonObject().get("size").getAsJsonObject().get("w").getAsInt();
               int sheetHeight = sheetConfigurationObject.get("meta").getAsJsonObject().get("size").getAsJsonObject().get("h").getAsInt();
               sheetConfigurationObject.get("frames")
                  .getAsJsonObject()
                  .entrySet()
                  .forEach(
                     entry -> {
                        Identifier texture = ObjModelLoader.resolveRelativePath(sheetConfiguration, basePath + (String)entry.getKey(), ".png");
                        JsonObject spriteObject = ((JsonElement)entry.getValue()).getAsJsonObject();
                        this.sprites
                           .put(
                              texture,
                              new AtlasSprite(
                                 sheetTexture,
                                 sheetWidth,
                                 sheetHeight,
                                 spriteObject.get("frame").getAsJsonObject().get("x").getAsInt(),
                                 spriteObject.get("frame").getAsJsonObject().get("y").getAsInt(),
                                 spriteObject.get("frame").getAsJsonObject().get("w").getAsInt(),
                                 spriteObject.get("frame").getAsJsonObject().get("h").getAsInt(),
                                 spriteObject.get("spriteSourceSize").getAsJsonObject().get("x").getAsInt(),
                                 spriteObject.get("spriteSourceSize").getAsJsonObject().get("y").getAsInt(),
                                 spriteObject.get("spriteSourceSize").getAsJsonObject().get("w").getAsInt(),
                                 spriteObject.get("spriteSourceSize").getAsJsonObject().get("h").getAsInt(),
                                 spriteObject.get("sourceSize").getAsJsonObject().get("w").getAsInt(),
                                 spriteObject.get("sourceSize").getAsJsonObject().get("h").getAsInt(),
                                 spriteObject.get("rotated").getAsBoolean()
                              )
                           );
                     }
                  );
            }
         );
      atlasConfigurationObject.get("noAtlas")
         .getAsJsonArray()
         .forEach(noAtlasObject -> this.noAtlasList.add(ObjModelLoader.resolveRelativePath(atlasConfiguration, basePath + noAtlasObject.getAsString(), ".png")));
   }

   public void applyToMesh(RawMesh mesh) {
      if (!this.noAtlasList.contains(mesh.materialProperties.getTexture())) {
         AtlasSprite sprite = this.sprites.get(mesh.materialProperties.getTexture());
         if (sprite != null) {
            sprite.applyToMesh(mesh);
         }
      }
   }

   public void clear() {
      this.sprites.clear();
   }
}
