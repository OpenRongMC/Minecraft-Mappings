package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class LivingEntity extends HolderBase<net.minecraft.world.entity.LivingEntity> {
   public LivingEntity(net.minecraft.world.entity.LivingEntity data) {
      super(data);
   }

}
