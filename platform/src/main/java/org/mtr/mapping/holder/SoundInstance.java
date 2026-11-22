package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SoundInstance extends HolderBase<net.minecraft.client.resources.sounds.SoundInstance> {
   public SoundInstance(net.minecraft.client.resources.sounds.SoundInstance data) {
      super(data);
   }

}
