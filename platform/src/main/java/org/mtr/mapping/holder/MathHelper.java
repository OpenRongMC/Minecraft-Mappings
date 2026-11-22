package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.Mth;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class MathHelper extends HolderBase<Mth> {
   public MathHelper(Mth data) {
      super(data);
   }

   @MappedMethod
   public static int floor(float f) {
      return Mth.floor(f);
   }

   @MappedMethod
   public static int floor(double d) {
      return Mth.floor(d);
   }

   @MappedMethod
   public static double frac(double d) {
      return Mth.frac(d);
   }

   @MappedMethod
   public static float frac(float f) {
      return Mth.frac(f);
   }

   @MappedMethod
   public static float clamp(float f, float g, float h) {
      return Mth.clamp(f, g, h);
   }

   @MappedMethod
   public static int clamp(int i, int j, int k) {
      return Mth.clamp(i, j, k);
   }

   @MappedMethod
   public static long clamp(long l, long m, long n) {
      return Mth.clamp(l, m, n);
   }

   @MappedMethod
   public static double clamp(double d, double e, double f) {
      return Mth.clamp(d, e, f);
   }

   @MappedMethod
   public static float getSqrtOfTwoMapped() {
      return Mth.SQRT_OF_TWO;
   }

   @MappedMethod
   public static double fractionalPart(double value) {
      return frac(value);
   }

   @MappedMethod
   public static float fractionalPart(float value) {
      return frac(value);
   }

   @MappedMethod
   public static float getSquareRootOfTwoMapped() {
      return getSqrtOfTwoMapped();
   }
}
