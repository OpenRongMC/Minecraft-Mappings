package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class RenderLayer extends HolderBase<RenderType> {
   public RenderLayer(RenderType data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getCutout() {
      return new RenderLayer(RenderTypes.cutoutMovingBlock());
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getTranslucent() {
      return new RenderLayer(RenderTypes.translucentMovingBlock());
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getEntityCutout(Identifier id) {
      return new RenderLayer(RenderTypes.entityCutout((net.minecraft.resources.Identifier)id.data));
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getEntityTranslucent(Identifier id) {
      return new RenderLayer(RenderTypes.entityTranslucent((net.minecraft.resources.Identifier)id.data));
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getLines() {
      return new RenderLayer(RenderTypes.lines());
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getEntityTranslucentCull(Identifier id) {
      return getEntityTranslucent(id);
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getBeaconBeam(Identifier id, boolean translucent) {
      return getEntityTranslucent(id);
   }

   @Nonnull
   @MappedMethod
   public static RenderLayer getText(Identifier id) {
      return getEntityCutout(id);
   }
}
