package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class DoorBlockAbstractMapping extends net.minecraft.world.level.block.DoorBlock {

   @MappedMethod
   protected void createBlockStateDefinition2(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      super.createBlockStateDefinition(builder);
   }

   @Deprecated
   protected final void createBlockStateDefinition(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      this.createBlockStateDefinition2(builder);
   }

   @Nonnull
   @MappedMethod
   public static BooleanProperty getOpenMapped() {
      return new BooleanProperty(OPEN);
   }


    public DoorBlockAbstractMapping(net.minecraft.world.level.block.state.properties.BlockSetType type, BlockSettings settings) { super(type, (net.minecraft.world.level.block.state.BlockBehaviour.Properties)settings.data); }
}
