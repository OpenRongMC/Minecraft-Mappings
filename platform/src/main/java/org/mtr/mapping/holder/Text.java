package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Text extends HolderBase<Component> {
   public Text(Component data) {
      super(data);
   }

   @MappedMethod
   public static Text cast(HolderBase<?> data) {
      return new Text((Component)data.data);
   }

   @Nonnull
   @MappedMethod
   public String getString(int i) {
      return ((Component)this.data).getString(i);
   }

   @Nonnull
   @MappedMethod
   public String getString() {
      return ((Component)this.data).getString();
   }

   @Nonnull
   @MappedMethod
   public static MutableText literal(String string) {
      return new MutableText(Component.literal(string));
   }

}
