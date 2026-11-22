package org.mtr.mapping.holder;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class FenceBlock extends HolderBase<net.minecraft.world.level.block.FenceBlock> {
   public FenceBlock(net.minecraft.world.level.block.FenceBlock data) {
      super(data);
   }

@MappedMethod
   public FenceBlock(BlockSettings properties) {
      super(new net.minecraft.world.level.block.FenceBlock((Properties)properties.data));
   }
}
