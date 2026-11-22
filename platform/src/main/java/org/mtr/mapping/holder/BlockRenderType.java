package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.block.RenderShape;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum BlockRenderType {
   INVISIBLE(RenderShape.INVISIBLE),
   MODEL(RenderShape.MODEL);

   public final RenderShape data;

   private BlockRenderType(RenderShape data) {
      this.data = data;
   }

   public static BlockRenderType convert(@Nullable RenderShape data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @Nonnull
   @MappedMethod
   public static BlockRenderType getModelMapped() {
      return convert(RenderShape.MODEL);
   }

   @MappedMethod
   public static BlockRenderType getEntityblockAnimatedMapped() {
      return INVISIBLE;
   }
}
