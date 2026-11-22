package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.Vec3i;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockPos extends HolderBase<net.minecraft.core.BlockPos> {
   public BlockPos(net.minecraft.core.BlockPos data) {
      super(data);
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((net.minecraft.core.BlockPos)this.data).equals(((HolderBase)data).data);
   }

   @Nonnull
   @MappedMethod
   public String toShortString() {
      return ((net.minecraft.core.BlockPos)this.data).toShortString();
   }

   @Nonnull
   @MappedMethod
   public BlockPos below(int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).below(i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos below() {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).below());
   }

   @Nonnull
   @MappedMethod
   public BlockPos offset(Vector3i vec3i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).offset((Vec3i)vec3i.data));
   }

   @Nonnull
   @MappedMethod
   public BlockPos offset(int i, int j, int k) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).offset(i, j, k));
   }

   @MappedMethod
   public static long offset(long l, Direction direction) {
      return net.minecraft.core.BlockPos.offset(l, direction.data);
   }

   @MappedMethod
   public static long offset(long l, int i, int j, int k) {
      return net.minecraft.core.BlockPos.offset(l, i, j, k);
   }

   @MappedMethod
   public int getZ() {
      return ((net.minecraft.core.BlockPos)this.data).getZ();
   }

   @MappedMethod
   public int getX() {
      return ((net.minecraft.core.BlockPos)this.data).getX();
   }

   @MappedMethod
   public int getY() {
      return ((net.minecraft.core.BlockPos)this.data).getY();
   }

   @Nonnull
   @MappedMethod
   public BlockPos west(int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).west(i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos west() {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).west());
   }

   @MappedMethod
   public double distSqr(Vector3i vec3i) {
      return ((net.minecraft.core.BlockPos)this.data).distSqr((Vec3i)vec3i.data);
   }

   @MappedMethod
   public static int getZ(long l) {
      return net.minecraft.core.BlockPos.getZ(l);
   }

   @MappedMethod
   public static int getY(long l) {
      return net.minecraft.core.BlockPos.getY(l);
   }

   @MappedMethod
   public static int getX(long l) {
      return net.minecraft.core.BlockPos.getX(l);
   }

   @Nonnull
   @MappedMethod
   public static BlockPos of(long l) {
      return new BlockPos(net.minecraft.core.BlockPos.of(l));
   }

   @Nonnull
   @MappedMethod
   public BlockPos east(int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).east(i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos east() {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).east());
   }

   @MappedMethod
   public long asLong() {
      return ((net.minecraft.core.BlockPos)this.data).asLong();
   }

   @MappedMethod
   public static long asLong(int i, int j, int k) {
      return net.minecraft.core.BlockPos.asLong(i, j, k);
   }

   @Nonnull
   @MappedMethod
   public BlockPos relative(Direction direction, int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).relative(direction.data, i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos relative(Direction direction) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).relative(direction.data));
   }

   @Nonnull
   @MappedMethod
   public BlockPos relative(Axis axis, int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).relative(axis.data, i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos above() {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).above());
   }

   @Nonnull
   @MappedMethod
   public BlockPos above(int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).above(i));
   }

   @MappedMethod
   public int distManhattan(Vector3i vec3i) {
      return ((net.minecraft.core.BlockPos)this.data).distManhattan((Vec3i)vec3i.data);
   }

   @Nonnull
   @MappedMethod
   public BlockPos south(int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).south(i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos south() {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).south());
   }

   @Nonnull
   @MappedMethod
   public BlockPos north() {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).north());
   }

   @Nonnull
   @MappedMethod
   public BlockPos north(int i) {
      return new BlockPos(((net.minecraft.core.BlockPos)this.data).north(i));
   }

   @Nonnull
   @MappedMethod
   public BlockPos up() {
      return this.above();
   }

   @Nonnull
   @MappedMethod
   public BlockPos up(int n) {
      return this.above(n);
   }

   @Nonnull
   @MappedMethod
   public BlockPos down() {
      return this.below();
   }

   @Nonnull
   @MappedMethod
   public BlockPos down(int n) {
      return this.below(n);
   }

   @Nonnull
   @MappedMethod
   public BlockPos offset(Direction direction) {
      return this.relative(direction);
   }

   @Nonnull
   @MappedMethod
   public BlockPos offset(Direction direction, int amount) {
      return this.relative(direction, amount);
   }

   @Nonnull
   @MappedMethod
   public static BlockPos fromLong(long packed) {
      return of(packed);
   }

   @MappedMethod
   public int getManhattanDistance(Vector3i other) {
      return this.distManhattan(other);
   }

   @Nonnull
   @MappedMethod
   public BlockPos add(int x, int y, int z) {
      return this.offset(x, y, z);
   }

   @Nonnull
   @MappedMethod
   public BlockPos offset(Axis axis, int amount) {
      return this.relative(axis, amount);
   }

@MappedMethod
   public BlockPos(int i, int j, int k) {
      super(new net.minecraft.core.BlockPos(i, j, k));
   }

@MappedMethod
   public BlockPos(Vector3i vec3i) {
      super(new net.minecraft.core.BlockPos(vec3i.getX(), vec3i.getY(), vec3i.getZ()));
   }
}
