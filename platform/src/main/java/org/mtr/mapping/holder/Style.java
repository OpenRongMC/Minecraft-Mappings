package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.network.chat.FontDescription;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Style extends HolderBase<net.minecraft.network.chat.Style> {
   public Style(net.minecraft.network.chat.Style data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Style withColor(TextFormatting chatFormatting) {
      return new Style(((net.minecraft.network.chat.Style)this.data).withColor(chatFormatting.data));
   }

   @Nonnull
   @MappedMethod
   public Style withColor(TextColor textColor) {
      return new Style(((net.minecraft.network.chat.Style)this.data).withColor((net.minecraft.network.chat.TextColor)textColor.data));
   }

   @Nonnull
   @MappedMethod
   public Style withColor(int i) {
      return new Style(((net.minecraft.network.chat.Style)this.data).withColor(i));
   }

   @Nonnull
   @MappedMethod
   public Style withItalic(Boolean boolean_) {
      return new Style(((net.minecraft.network.chat.Style)this.data).withItalic(boolean_));
   }

   @Nonnull
   @MappedMethod
   public Style withBold(Boolean boolean_) {
      return new Style(((net.minecraft.network.chat.Style)this.data).withBold(boolean_));
   }

   @Nonnull
   @MappedMethod
   public Style withFont(FontDescription fontDescription) {
      return new Style(((net.minecraft.network.chat.Style)this.data).withFont(fontDescription));
   }

   @Nonnull
   @MappedMethod
   public static Style getEmptyMapped() {
      return new Style(net.minecraft.network.chat.Style.EMPTY);
   }

   @Nonnull
   @MappedMethod
   public Style withFont(Identifier identifier) {
      return this.withFont(new net.minecraft.network.chat.FontDescription.Resource((net.minecraft.resources.Identifier)identifier.data));
   }
}
