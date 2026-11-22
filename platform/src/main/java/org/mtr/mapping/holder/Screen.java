package org.mtr.mapping.holder;

import com.mojang.blaze3d.platform.InputConstants;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.Minecraft;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Screen extends HolderBase<net.minecraft.client.gui.screens.Screen> {
   public Screen(net.minecraft.client.gui.screens.Screen data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Text getTitle() {
      return new Text(((net.minecraft.client.gui.screens.Screen)this.data).getTitle());
   }

   @MappedMethod
   public static boolean hasShiftDown() {
      com.mojang.blaze3d.platform.Window win = Minecraft.getInstance().getWindow();
      return InputConstants.isKeyDown(InputConstants.KEY_LSHIFT) || InputConstants.isKeyDown(InputConstants.KEY_RSHIFT);
   }

}
