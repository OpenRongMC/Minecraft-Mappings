package org.mtr.mapping.holder;

import java.util.function.UnaryOperator;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class MutableText extends HolderBase<MutableComponent> {
   public MutableText(MutableComponent data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public String getString(int i) {
      return ((MutableComponent)this.data).getString(i);
   }

   @Nonnull
   @MappedMethod
   public String getString() {
      return ((MutableComponent)this.data).getString();
   }

   @Nonnull
   @MappedMethod
   public MutableText withStyle(TextFormatting chatFormatting) {
      return new MutableText(((MutableComponent)this.data).withStyle(chatFormatting.data));
   }

   @Nonnull
   @MappedMethod
   public MutableText withStyle(Style style) {
      return new MutableText(((MutableComponent)this.data).withStyle((net.minecraft.network.chat.Style)style.data));
   }

   @Nonnull
   @MappedMethod
   public MutableText withStyle(ChatFormatting[] chatFormattings) {
      return new MutableText(((MutableComponent)this.data).withStyle(chatFormattings));
   }

   @Nonnull
   @MappedMethod
   public MutableText withStyle(UnaryOperator<net.minecraft.network.chat.Style> unaryOperator) {
      return new MutableText(((MutableComponent)this.data).withStyle(unaryOperator));
   }

   @Nonnull
   @MappedMethod
   public MutableText formatted(TextFormatting formatting) {
      return this.withStyle(formatting);
   }
}
