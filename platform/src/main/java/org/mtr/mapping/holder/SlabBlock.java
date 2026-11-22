package org.mtr.mapping.holder;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;


import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SlabBlock extends HolderBase<net.minecraft.world.level.block.SlabBlock> {
   public SlabBlock(net.minecraft.world.level.block.SlabBlock data) {
      super(data);
   }

   @MappedMethod
   public static boolean isInstance(@Nullable HolderBase<?> data) {
      return data != null && data.data instanceof net.minecraft.world.level.block.SlabBlock;
   }

@MappedMethod
   public SlabBlock(BlockSettings properties) {
      super(new net.minecraft.world.level.block.SlabBlock((Properties)properties.data));
   }
}
