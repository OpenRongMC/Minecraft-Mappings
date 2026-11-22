package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.components.AbstractWidget;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ClickableWidget extends HolderBase<AbstractWidget> {
   public ClickableWidget(AbstractWidget data) {
      super(data);
   }

}
