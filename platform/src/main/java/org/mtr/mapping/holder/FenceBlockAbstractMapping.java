package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class FenceBlockAbstractMapping extends net.minecraft.world.level.block.FenceBlock {

   @MappedMethod
   protected void createBlockStateDefinition2(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      super.createBlockStateDefinition(builder);
   }

   @Deprecated
   protected final void createBlockStateDefinition(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      this.createBlockStateDefinition2(builder);
   }

@MappedMethod
   public FenceBlockAbstractMapping(BlockSettings properties) {
      super((Properties)properties.data);
   }
}
