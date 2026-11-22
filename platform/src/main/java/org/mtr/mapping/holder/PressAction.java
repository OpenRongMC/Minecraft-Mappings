package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.OnPress;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public interface PressAction extends OnPress {
   @MappedMethod
   void onPress2(ButtonWidget var1);

   @Deprecated
   default void onPress(Button button) {
      this.onPress2(new ButtonWidget(button));
   }
}
