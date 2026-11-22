package org.mtr.mapping.holder;
import net.minecraft.client.gui.Font.Provider;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.Font;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class TextRenderer extends HolderBase<Font> {
   public TextRenderer(Font data) {
      super(data);
   }

@MappedMethod
   public TextRenderer(Provider provider) {
      super(new Font(provider));
   }
}
