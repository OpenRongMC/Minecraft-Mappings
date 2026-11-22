package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum Direction {
   DOWN(net.minecraft.core.Direction.DOWN),
   UP(net.minecraft.core.Direction.UP),
   NORTH(net.minecraft.core.Direction.NORTH),
   SOUTH(net.minecraft.core.Direction.SOUTH),
   WEST(net.minecraft.core.Direction.WEST),
   EAST(net.minecraft.core.Direction.EAST);

   public final net.minecraft.core.Direction data;

   private Direction(net.minecraft.core.Direction data) {
      this.data = data;
   }

   public static Direction convert(@Nullable net.minecraft.core.Direction data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @MappedMethod
   public float toYRot() {
      return this.data.toYRot();
   }

   @Nonnull
   @MappedMethod
   public Vector3i getUnitVec3i() {
      return new Vector3i(this.data.getUnitVec3i());
   }

   @Nonnull
   @MappedMethod
   public Direction getCounterClockWise(Axis axis) {
      return convert(this.data.getCounterClockWise(axis.data));
   }

   @Nonnull
   @MappedMethod
   public Direction getCounterClockWise() {
      return convert(this.data.getCounterClockWise());
   }

   @Nonnull
   @MappedMethod
   public Direction getClockWise(Axis axis) {
      return convert(this.data.getClockWise(axis.data));
   }

   @Nonnull
   @MappedMethod
   public Direction getClockWise() {
      return convert(this.data.getClockWise());
   }

   @Nonnull
   @MappedMethod
   public static Direction fromYRot(double d) {
      return convert(net.minecraft.core.Direction.fromYRot(d));
   }

   @MappedMethod
   public int get2DDataValue() {
      return this.data.get2DDataValue();
   }

   @Nonnull
   @MappedMethod
   public static Direction from2DDataValue(int i) {
      return convert(net.minecraft.core.Direction.from2DDataValue(i));
   }

   @Nonnull
   @MappedMethod
   public Axis getAxis() {
      return Axis.convert(this.data.getAxis());
   }

   @MappedMethod
   public int getStepZ() {
      return this.data.getStepZ();
   }

   @MappedMethod
   public int getStepX() {
      return this.data.getStepX();
   }

   @MappedMethod
   public int getStepY() {
      return this.data.getStepY();
   }

   @Nonnull
   @MappedMethod
   public Direction getOpposite() {
      return convert(this.data.getOpposite());
   }

   @Nonnull
   @MappedMethod
   public Direction rotateYClockwise() {
      return this.getClockWise();
   }

   @Nonnull
   @MappedMethod
   public Direction rotateYCounterclockwise() {
      return this.getCounterClockWise();
   }

   @Nonnull
   @MappedMethod
   public static Direction fromHorizontal(int value) {
      return from2DDataValue(value);
   }

   @MappedMethod
   public int getOffsetX() {
      return this.getStepX();
   }

   @MappedMethod
   public int getOffsetY() {
      return this.getStepY();
   }

   @MappedMethod
   public int getOffsetZ() {
      return this.getStepZ();
   }

   @MappedMethod
   public float asRotation() {
      return this.toYRot();
   }

   @Nonnull
   @MappedMethod
   public static Direction fromRotation(float rotation) {
      return fromYRot(rotation);
   }

   @MappedMethod
   public int getHorizontal() {
      return this.get2DDataValue();
   }
}
