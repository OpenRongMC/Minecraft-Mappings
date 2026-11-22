package org.mtr.mapping.mapper;

import java.util.List;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.StairsShape;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.holder.StairShape;
import org.mtr.mapping.holder.StairsBlockAbstractMapping;
import org.mtr.mapping.tool.HolderBase;

public class StairsBlockExtension extends StairsBlockAbstractMapping implements BlockHelper {
   @MappedMethod
   public StairsBlockExtension(org.mtr.mapping.holder.BlockState baseBlockState, BlockSettings blockSettings) {
      super(baseBlockState, blockSettings);
   }

   @Deprecated
   @Override
   protected void createBlockStateDefinition2(Builder<Block, BlockState> builder) {
      this.appendPropertiesHelper(builder);
   }

   @MappedMethod
   @Override
   public void addBlockProperties(List<HolderBase<?>> properties) {
      properties.add(new Property(FACING));
      properties.add(new Property(HALF));
      properties.add(new Property(SHAPE));
      properties.add(new Property(WATERLOGGED));
   }

   @MappedMethod
   public static StairShape getType(org.mtr.mapping.holder.BlockState state) {
      return StairShape.convert((StairsShape)((BlockState)state.data).getValue(SHAPE));
   }
}
