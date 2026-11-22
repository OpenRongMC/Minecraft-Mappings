package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ButtonWidget extends HolderBase<Button> {
   public ButtonWidget(Button data) {
      super(data);
   }

   @MappedMethod
   public void setMessage(Text component) {
      ((Button)this.data).setMessage((Component)component.data);
   }

   

   public void setActiveMapped(boolean active) { ((Button)data).active = active; }
}
