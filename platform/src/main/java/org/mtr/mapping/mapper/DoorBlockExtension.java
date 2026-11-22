package org.mtr.mapping.mapper;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.DoorBlockAbstractMapping;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.tool.HolderBase;

public class DoorBlockExtension extends DoorBlockAbstractMapping implements BlockHelper {
   @MappedMethod
   public DoorBlockExtension(boolean canOpenByHand, Consumer<BlockSettings> consumer) {
      super(canOpenByHand ? BlockSetType.OAK : BlockSetType.IRON, getBlockSettings(consumer));
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
      properties.add(new Property(OPEN));
      properties.add(new Property(HINGE));
      properties.add(new Property(POWERED));
      properties.add(new Property(HALF));
   }

   private static BlockSettings getBlockSettings(Consumer<BlockSettings> consumer) {
      BlockSettings blockSettings = BlockHelper.createBlockSettings(true, true);
      consumer.accept(blockSettings);
      return blockSettings;
   }
}
