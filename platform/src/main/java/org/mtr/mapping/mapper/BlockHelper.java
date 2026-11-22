package org.mtr.mapping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.TooltipContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.VoxelShapes;
import org.mtr.mapping.tool.DummyInterface;
import org.mtr.mapping.tool.HolderBase;

public interface BlockHelper extends DummyInterface {
   @MappedMethod
   default void addBlockProperties(List<HolderBase<?>> properties) {
   }

   @Deprecated
   default void appendPropertiesHelper(Builder<Block, BlockState> builder) {
      List<HolderBase<?>> properties = new ArrayList<>();
      this.addBlockProperties(properties);
      if (!properties.isEmpty()) {
         Property<?>[] newProperties = new Property[properties.size()];

         for (int i = 0; i < properties.size(); i++) {
            Object data = properties.get(i).data;
            if (data instanceof Property) {
               newProperties[i] = (Property<?>)data;
            }
         }

         builder.add(newProperties);
      }
   }

   @MappedMethod
   default void addTooltips(ItemStack stack, @Nullable BlockView world, List<MutableText> tooltip, TooltipContext options) {
   }

   @Deprecated
   default void appendTooltipHelper(ItemStack stack, @Nullable BlockView world, Consumer<Component> tooltip, TooltipContext options) {
      List<MutableText> newTooltipList = new ArrayList<>();
      this.addTooltips(stack, world, newTooltipList, options);
      newTooltipList.forEach(mutableText -> tooltip.accept((Component)mutableText.data));
   }

   @MappedMethod
   static BlockSettings setLuminance(BlockSettings blockSettings, ToIntFunction<org.mtr.mapping.holder.BlockState> luminanceFunction) {
      return new BlockSettings(
         ((Properties)blockSettings.data).lightLevel(blockState -> luminanceFunction.applyAsInt(new org.mtr.mapping.holder.BlockState(blockState)))
      );
   }

   @MappedMethod
   static BlockSettings createBlockSettings(boolean blockPiston, boolean forceSolid) {
      Properties settings = Properties.of().pushReaction(blockPiston ? PushReaction.IMMOVEABLE : PushReaction.PUSH_PULL);
      return new BlockSettings(forceSolid ? settings.forceSolidOn() : settings);
   }

   @MappedMethod
   static BlockSettings createBlockSettings(boolean blockPiston, boolean forceSolid, ToIntFunction<org.mtr.mapping.holder.BlockState> luminanceFunction) {
      return setLuminance(createBlockSettings(blockPiston, forceSolid), luminanceFunction);
   }

   @MappedMethod
   static VoxelShape shapeUnion(VoxelShape voxelShape, VoxelShape... voxelShapes) {
      VoxelShape result = voxelShape;

      for (VoxelShape additionalShape : voxelShapes) {
         result = VoxelShapes.or(result, additionalShape);
      }

      return result;
   }
}
