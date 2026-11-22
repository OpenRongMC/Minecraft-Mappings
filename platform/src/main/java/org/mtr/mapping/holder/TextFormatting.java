package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.ChatFormatting;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum TextFormatting {
   BLACK(ChatFormatting.BLACK),
   DARK_BLUE(ChatFormatting.DARK_BLUE),
   DARK_GREEN(ChatFormatting.DARK_GREEN),
   DARK_AQUA(ChatFormatting.DARK_AQUA),
   DARK_RED(ChatFormatting.DARK_RED),
   DARK_PURPLE(ChatFormatting.DARK_PURPLE),
   GOLD(ChatFormatting.GOLD),
   GRAY(ChatFormatting.GRAY),
   DARK_GRAY(ChatFormatting.DARK_GRAY),
   BLUE(ChatFormatting.BLUE),
   GREEN(ChatFormatting.GREEN),
   AQUA(ChatFormatting.AQUA),
   RED(ChatFormatting.RED),
   LIGHT_PURPLE(ChatFormatting.LIGHT_PURPLE),
   YELLOW(ChatFormatting.YELLOW),
   WHITE(ChatFormatting.WHITE),
   OBFUSCATED(ChatFormatting.OBFUSCATED),
   BOLD(ChatFormatting.BOLD),
   STRIKETHROUGH(ChatFormatting.STRIKETHROUGH),
   UNDERLINE(ChatFormatting.UNDERLINE),
   ITALIC(ChatFormatting.ITALIC),
   RESET(ChatFormatting.RESET);

   public final ChatFormatting data;

   private TextFormatting(ChatFormatting data) {
      this.data = data;
   }

   public static TextFormatting convert(@Nullable ChatFormatting data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @Nonnull
   @MappedMethod
   public Integer getColor() {
      return net.minecraft.network.chat.TextColor.fromLegacyFormat(this.data) == null ? null : net.minecraft.network.chat.TextColor.fromLegacyFormat(this.data).getValue();
   }

   @Nonnull
   @MappedMethod
   public static TextFormatting getWhiteMapped() {
      return convert(ChatFormatting.WHITE);
   }

   @Nullable
   @MappedMethod
   public Integer getColorValue() {
      return this.getColor();
   }
}
