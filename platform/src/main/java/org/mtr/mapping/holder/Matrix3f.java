package org.mtr.mapping.holder;


import java.nio.FloatBuffer;
import javax.annotation.ParametersAreNonnullByDefault;
import org.joml.Matrix2fc;
import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Matrix3f extends HolderBase<org.joml.Matrix3f> {
   public Matrix3f(org.joml.Matrix3f data) {
      super(data);
   }

@MappedMethod
   public Matrix3f(Matrix4fc arg0) {
      super(new org.joml.Matrix3f(arg0));
   }



@MappedMethod
   public Matrix3f(FloatBuffer arg0) {
      super(new org.joml.Matrix3f(arg0));
   }





@MappedMethod
   public Matrix3f(Matrix2fc arg0) {
      super(new org.joml.Matrix3f(arg0));
   }

@MappedMethod
   public Matrix3f(Matrix3fc arg0) {
      super(new org.joml.Matrix3f(arg0));
   }
}
