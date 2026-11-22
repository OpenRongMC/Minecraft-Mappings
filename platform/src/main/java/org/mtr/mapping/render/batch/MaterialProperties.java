package org.mtr.mapping.render.batch;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Util;
import org.lwjgl.opengl.GL33;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.VertexFormats;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mapping.mapper.RenderLayerHelper;
import org.mtr.mapping.render.shader.GlTextureBinder;
import org.mtr.mapping.render.vertex.VertexAttributeState;

public final class MaterialProperties {
   private Identifier texture;
   public final OptimizedModel.ShaderType shaderType;
   public final VertexAttributeState vertexAttributeState;
   public final boolean translucent;
   public final boolean writeDepthBuf;
   public final boolean cutoutHack;
   private static final Function<Identifier, RenderLayer> ENTITY_TRANSLUCENT_CULL = Util.memoize(
      texture -> RenderLayerHelper.createTriangles(
         "entity_translucent_cull_triangles",
         VertexFormats.getNewEntityMapped(),
         256,
         true,
         true,
         new RenderLayer(RenderTypes.entityTranslucent((net.minecraft.resources.Identifier)texture.data))
      )
   );
   private static final BiFunction<Identifier, Boolean, RenderLayer> BEACON_BEAM = Util.memoize(
      (texture, translucent) -> RenderLayerHelper.createTriangles(
         "beacon_beam_triangles",
         VertexFormats.getNewEntityMapped(),
         256,
         false,
         translucent,
         new RenderLayer(RenderTypes.beaconBeam((net.minecraft.resources.Identifier)texture.data, translucent))
      )
   );
   private static final Function<Identifier, RenderLayer> ENTITY_CUTOUT = Util.memoize(
      texture -> RenderLayerHelper.createTriangles(
         "entity_cutout_triangles",
         VertexFormats.getNewEntityMapped(),
         256,
         true,
         false,
         new RenderLayer(RenderTypes.entityCutout((net.minecraft.resources.Identifier)texture.data))
      )
   );

   public MaterialProperties(OptimizedModel.ShaderType shaderType, Identifier texture, @Nullable Integer color) {
      this.shaderType = shaderType;
      this.texture = texture;
      switch (shaderType) {
         case TRANSLUCENT:
            this.translucent = true;
            this.writeDepthBuf = true;
            this.cutoutHack = false;
            this.vertexAttributeState = new VertexAttributeState(color, null);
            break;
         case CUTOUT_BRIGHT:
            this.translucent = false;
            this.writeDepthBuf = true;
            this.cutoutHack = false;
            this.vertexAttributeState = new VertexAttributeState(color, 15728880);
            break;
         case TRANSLUCENT_BRIGHT:
            this.translucent = true;
            this.writeDepthBuf = true;
            this.cutoutHack = false;
            this.vertexAttributeState = new VertexAttributeState(color, 15728880);
            break;
         case CUTOUT_GLOWING:
            this.translucent = false;
            this.writeDepthBuf = true;
            this.cutoutHack = true;
            this.vertexAttributeState = new VertexAttributeState(color, null);
            break;
         case TRANSLUCENT_GLOWING:
            this.translucent = true;
            this.writeDepthBuf = false;
            this.cutoutHack = false;
            this.vertexAttributeState = new VertexAttributeState(color, null);
            break;
         default:
            this.translucent = false;
            this.writeDepthBuf = true;
            this.cutoutHack = false;
            this.vertexAttributeState = new VertexAttributeState(color, null);
      }
   }

   public void setupCompositeState() {
      int textureId = GlTextureBinder.bind(this.texture);
      if (textureId == 0) {
         GL33.glBindTexture(3553, 0);
      }

      if (!this.translucent && !this.cutoutHack) {
         GL33.glDisable(3042);
      } else {
         GL33.glEnable(3042);
         GL33.glBlendFuncSeparate(770, 771, 1, 771);
      }

      GL33.glEnable(2929);
      GL33.glDepthFunc(515);
      GL33.glEnable(2884);
      GL33.glDepthMask(this.writeDepthBuf);
   }

   public RenderLayer getBlazeRenderType() {
      switch (this.shaderType) {
         case TRANSLUCENT:
         case TRANSLUCENT_BRIGHT:
            return ENTITY_TRANSLUCENT_CULL.apply(this.texture);
         case CUTOUT_BRIGHT:
         default:
            return ENTITY_CUTOUT.apply(this.texture);
         case CUTOUT_GLOWING:
         case TRANSLUCENT_GLOWING:
            return BEACON_BEAM.apply(this.texture, this.translucent);
      }
   }

   public Identifier getTexture() {
      return this.texture;
   }

   public void setTexture(Identifier texture) {
      this.texture = texture;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         return !(object instanceof MaterialProperties materialProperties)
            ? false
            : this.shaderType == materialProperties.shaderType
               && Objects.equals(this.texture, materialProperties.texture)
               && Objects.equals(this.vertexAttributeState, materialProperties.vertexAttributeState)
               && this.translucent == materialProperties.translucent
               && this.writeDepthBuf == materialProperties.writeDepthBuf
               && this.cutoutHack == materialProperties.cutoutHack;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.shaderType, this.texture, this.vertexAttributeState, this.translucent, this.writeDepthBuf, this.cutoutHack);
   }
}
