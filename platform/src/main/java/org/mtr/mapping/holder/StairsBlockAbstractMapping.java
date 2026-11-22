package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class StairsBlockAbstractMapping extends StairBlock {

   @MappedMethod
   protected void createBlockStateDefinition2(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      super.createBlockStateDefinition(builder);
   }

   @Deprecated
   protected final void createBlockStateDefinition(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      this.createBlockStateDefinition2(builder);
   }


    public StairsBlockAbstractMapping(BlockState state, BlockSettings settings) { super((net.minecraft.world.level.block.state.BlockState)state.data, (net.minecraft.world.level.block.state.BlockBehaviour.Properties)settings.data); }
}
