package org.mtr.mapping.render.shader;

import com.mojang.renderpearl.backend.opengl.GlTexture;
import javax.annotation.Nullable;
import org.lwjgl.opengl.GL33;
import org.mtr.mapping.holder.AbstractTexture;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MinecraftClient;

public final class GlTextureBinder {
   public static int bind(@Nullable Identifier identifier) {
      GL33.glActiveTexture(33984);
      if (identifier == null) {
         GL33.glBindTexture(3553, 0);
         return 0;
      } else {
         AbstractTexture abstractTexture = MinecraftClient.getInstance().getTextureManager().getTexture(identifier);
         if (((net.minecraft.client.renderer.texture.AbstractTexture)abstractTexture.data).getTexture() instanceof GlTexture glTexture) {
            int glId = glTexture.glId();
            GL33.glBindTexture(3553, glId);
            return glId;
         } else {
            GL33.glBindTexture(3553, 0);
            return 0;
         }
      }
   }
}
