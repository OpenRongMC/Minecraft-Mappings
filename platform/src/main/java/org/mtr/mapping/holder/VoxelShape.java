package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class VoxelShape extends HolderBase<net.minecraft.world.phys.shapes.VoxelShape> {
   public VoxelShape(net.minecraft.world.phys.shapes.VoxelShape data) {
      super(data);
   }

}
