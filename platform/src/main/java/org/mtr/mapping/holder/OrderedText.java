package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.FormattedCharSequence;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class OrderedText extends HolderBase<FormattedCharSequence> {
   public OrderedText(FormattedCharSequence data) {
      super(data);
   }

}
