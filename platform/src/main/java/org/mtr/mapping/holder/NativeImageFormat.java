package org.mtr.mapping.holder;

import com.mojang.blaze3d.platform.NativeImage.Format;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum NativeImageFormat {
   RGBA(Format.RGBA),
   RGB(Format.RGB),
   LUMINANCE_ALPHA(Format.LUMINANCE_ALPHA),
   LUMINANCE(Format.LUMINANCE);

   public final Format data;

   private NativeImageFormat(Format data) {
      this.data = data;
   }

   public static NativeImageFormat convert(@Nullable Format data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @MappedMethod
   public static NativeImageFormat getAbgrMapped() {
      return RGBA;
   }
}
