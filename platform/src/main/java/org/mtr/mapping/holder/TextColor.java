package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class TextColor extends HolderBase<net.minecraft.network.chat.TextColor> {
   public TextColor(net.minecraft.network.chat.TextColor data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static TextColor fromRgb(int i) {
      return new TextColor(net.minecraft.network.chat.TextColor.fromRgb(i));
   }

}
