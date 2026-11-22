package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class AbstractSoundInstance extends HolderBase<net.minecraft.client.resources.sounds.AbstractSoundInstance> {
   public AbstractSoundInstance(net.minecraft.client.resources.sounds.AbstractSoundInstance data) {
      super(data);
   }

}
