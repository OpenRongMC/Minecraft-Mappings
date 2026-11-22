package org.mtr.mapping.mapper;

import java.util.List;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.FenceBlockAbstractMapping;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.tool.HolderBase;

public class FenceBlockExtension extends FenceBlockAbstractMapping implements BlockHelper {
   @MappedMethod
   public FenceBlockExtension(BlockSettings settings) {
      super(settings);
   }

   @Deprecated
   @Override
   protected void createBlockStateDefinition2(Builder<Block, BlockState> builder) {
      this.appendPropertiesHelper(builder);
   }

   @MappedMethod
   @Override
   public void addBlockProperties(List<HolderBase<?>> properties) {
      properties.add(new Property(NORTH));
      properties.add(new Property(EAST));
      properties.add(new Property(SOUTH));
      properties.add(new Property(WEST));
      properties.add(new Property(WATERLOGGED));
   }
}
