package org.mtr.mapping.render.obj;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.render.model.RawMesh;
import org.mtr.mapping.render.vertex.Vertex;
import org.mtr.mapping.tool.DummyClass;

public final class AtlasSprite {
   public final Identifier sheet;
   public final int sheetWidth;
   public final int sheetHeight;
   public final int frameX;
   public final int frameY;
   public final int frameWidth;
   public final int frameHeight;
   public final int spriteX;
   public final int spriteY;
   public final int spriteWidth;
   public final int spriteHeight;
   public final int sourceWidth;
   public final int sourceHeight;
   public final boolean rotated;

   public AtlasSprite(
      Identifier sheet,
      int sheetWidth,
      int sheetHeight,
      int frameX,
      int frameY,
      int frameWidth,
      int frameHeight,
      int spriteX,
      int spriteY,
      int spriteWidth,
      int spriteHeight,
      int sourceWidth,
      int sourceHeight,
      boolean rotated
   ) {
      this.sheet = sheet;
      this.sheetWidth = sheetWidth;
      this.sheetHeight = sheetHeight;
      this.frameX = frameX;
      this.frameY = frameY;
      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;
      this.spriteX = spriteX;
      this.spriteY = spriteY;
      this.spriteWidth = spriteWidth;
      this.spriteHeight = spriteHeight;
      this.sourceWidth = sourceWidth;
      this.sourceHeight = sourceHeight;
      this.rotated = rotated;
   }

   public void applyToMesh(RawMesh mesh) {
      boolean uvBleeding = false;

      for (Vertex vertex : mesh.vertices) {
         vertex.u = mapRange(vertex.u, (float)this.spriteX / this.sourceWidth, (float)(this.spriteX + this.spriteWidth) / this.sourceWidth, 0.0F, 1.0F);
         vertex.v = mapRange(vertex.v, (float)this.spriteY / this.spriteHeight, (float)(this.spriteY + this.spriteHeight) / this.sourceHeight, 0.0F, 1.0F);
         if (vertex.u < 0.0F || vertex.u > 1.0F || vertex.v < 0.0F || vertex.v > 1.0F) {
            uvBleeding = true;
         }

         vertex.u = mapRange(vertex.u, 0.0F, 1.0F, (float)this.frameX / this.sheetWidth, (float)(this.frameX + this.frameWidth) / this.sheetWidth);
         vertex.v = mapRange(vertex.v, 0.0F, 1.0F, (float)this.frameY / this.sheetHeight, (float)(this.frameY + this.frameHeight) / this.sheetHeight);
      }

      if (uvBleeding) {
         DummyClass.logInfo("UV bleeding into adjacent sprite in " + mesh.materialProperties.getTexture());
      }

      mesh.materialProperties.setTexture(this.sheet);
   }

   private static float mapRange(float x, float inMin, float inMax, float outMin, float outMax) {
      return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
   }
}
