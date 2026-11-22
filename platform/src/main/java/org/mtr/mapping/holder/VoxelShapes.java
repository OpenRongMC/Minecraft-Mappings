package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class VoxelShapes extends HolderBase<Shapes> {
   public VoxelShapes(Shapes data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape or(VoxelShape voxelShape, VoxelShape voxelShape2) {
      return new VoxelShape(
         Shapes.or((net.minecraft.world.phys.shapes.VoxelShape)voxelShape.data, (net.minecraft.world.phys.shapes.VoxelShape)voxelShape2.data)
      );
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape or(VoxelShape voxelShape, net.minecraft.world.phys.shapes.VoxelShape[] voxelShapes) {
      return new VoxelShape(Shapes.or((net.minecraft.world.phys.shapes.VoxelShape)voxelShape.data, voxelShapes));
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape join(VoxelShape voxelShape, VoxelShape voxelShape2, BooleanBiFunction booleanOp) {
      return new VoxelShape(
         Shapes.join(
            (net.minecraft.world.phys.shapes.VoxelShape)voxelShape.data,
            (net.minecraft.world.phys.shapes.VoxelShape)voxelShape2.data,
            (BooleanOp)booleanOp.data
         )
      );
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape block() {
      return new VoxelShape(Shapes.block());
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape box(double d, double e, double f, double g, double h, double i) {
      return new VoxelShape(Shapes.box(d, e, f, g, h, i));
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape empty() {
      return new VoxelShape(Shapes.empty());
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape fullCube() {
      return block();
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape union(VoxelShape a, VoxelShape b) {
      return or(a, b);
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape union(VoxelShape a, VoxelShape... more) {
      VoxelShape r = a;

      for (VoxelShape s : more) {
         r = or(r, s);
      }

      return r;
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape combine(VoxelShape a, VoxelShape b, BooleanBiFunction function) {
      return join(a, b, function);
   }

   @Nonnull
   @MappedMethod
   public static VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
      return box(x1, y1, z1, x2, y2, z2);
   }
}
