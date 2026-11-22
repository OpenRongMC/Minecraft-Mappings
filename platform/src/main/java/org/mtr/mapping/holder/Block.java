package org.mtr.mapping.holder;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Block extends HolderBase<net.minecraft.world.level.block.Block> {
   public Block(net.minecraft.world.level.block.Block data) {
      super(data);
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((net.minecraft.world.level.block.Block)this.data).equals(((HolderBase)data).data);
   }

   @MappedMethod
   public static int getId(BlockState blockState) {
      return net.minecraft.world.level.block.Block.getId((net.minecraft.world.level.block.state.BlockState)blockState.data);
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape box(double d, double e, double f, double g, double h, double i) {
      return new VoxelShape(net.minecraft.world.level.block.Block.box(d, e, f, g, h, i));
   }

   @Nonnull
   @MappedMethod
   public static BlockState stateById(int i) {
      return new BlockState(net.minecraft.world.level.block.Block.stateById(i));
   }

   @Nonnull
   @MappedMethod
   public Item asItem() {
      return new Item(((net.minecraft.world.level.block.Block)this.data).asItem());
   }

   @Nonnull
   @MappedMethod
   public final BlockState defaultBlockState() {
      return new BlockState(((net.minecraft.world.level.block.Block)this.data).defaultBlockState());
   }

   @Nonnull
   @MappedMethod
   public MutableText getName() {
      return new MutableText(((net.minecraft.world.level.block.Block)this.data).getName());
   }

   @Nonnull
   @MappedMethod
   public final String getDescriptionId() {
      return ((net.minecraft.world.level.block.Block)this.data).getDescriptionId();
   }

   @Nonnull
   @MappedMethod
   public MapColor defaultMapColor() {
      return new MapColor(((net.minecraft.world.level.block.Block)this.data).defaultMapColor());
   }

   @Nonnull
   @MappedMethod
   public static Block byItem(Item item) {
      return new Block(net.minecraft.world.level.block.Block.byItem((net.minecraft.world.item.Item)item.data));
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape createCuboidShape(double x1, double y1, double z1, double x2, double y2, double z2) {
      return box(x1, y1, z1, x2, y2, z2);
   }

   @Nonnull
   @MappedMethod
   public final BlockState getDefaultState() {
      return this.defaultBlockState();
   }

   @MappedMethod
   public static int getRawIdFromState(BlockState state) {
      return getId(state);
   }

   @Nonnull
   @MappedMethod
   public static BlockState getStateFromRawId(int id) {
      return stateById(id);
   }

   @Nonnull
   @MappedMethod
   public String getTranslationKey() {
      return this.getDescriptionId();
   }

   @Nonnull
   @MappedMethod
   public static Block getBlockFromItem(Item item) {
      return byItem(item);
   }

   @Nonnull
   @MappedMethod
   public MapColor getDefaultMapColor() {
      return this.defaultMapColor();
   }

@MappedMethod
   public Block(BlockSettings properties) {
      super(new net.minecraft.world.level.block.Block((Properties)properties.data));
   }
}
