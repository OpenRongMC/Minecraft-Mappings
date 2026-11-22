package org.mtr.mapping.holder;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class VertexFormats extends HolderBase<DefaultVertexFormat> {
   public VertexFormats(DefaultVertexFormat data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static VertexFormat getNewEntityMapped() {
      return new VertexFormat(DefaultVertexFormat.ENTITY);
   }

}
