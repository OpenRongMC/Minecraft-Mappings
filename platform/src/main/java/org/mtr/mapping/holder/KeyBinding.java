package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.KeyMapping;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class KeyBinding extends HolderBase<KeyMapping> {
   public KeyBinding(KeyMapping data) {
      super(data);
   }

   @MappedMethod
   public boolean consumeClick() {
      return ((KeyMapping)this.data).consumeClick();
   }

   @Nonnull
   @MappedMethod
   public Text getTranslatedKeyMessage() {
      return new Text(((KeyMapping)this.data).getTranslatedKeyMessage());
   }

   @MappedMethod
   public boolean isDown() {
      return ((KeyMapping)this.data).isDown();
   }

   @MappedMethod
   public boolean isPressed() {
      return this.isDown();
   }

   @Nonnull
   @MappedMethod
   public Text getBoundKeyLocalizedText() {
      return this.getTranslatedKeyMessage();
   }






}
