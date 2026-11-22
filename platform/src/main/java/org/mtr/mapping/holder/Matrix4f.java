package org.mtr.mapping.holder;


import java.nio.FloatBuffer;
import javax.annotation.ParametersAreNonnullByDefault;
import org.joml.Matrix3fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Matrix4f extends HolderBase<org.joml.Matrix4f> {
   public Matrix4f(org.joml.Matrix4f data) {
      super(data);
   }

@MappedMethod
   public Matrix4f(Matrix4dc arg0) {
      super(new org.joml.Matrix4f(arg0));
   }



@MappedMethod
   public Matrix4f(FloatBuffer arg0) {
      super(new org.joml.Matrix4f(arg0));
   }



@MappedMethod
   public Matrix4f() {
      super(new org.joml.Matrix4f());
   }

@MappedMethod
   public Matrix4f(Matrix3fc arg0) {
      super(new org.joml.Matrix4f(arg0));
   }

@MappedMethod
   public Matrix4f(Matrix4fc arg0) {
      super(new org.joml.Matrix4f(arg0));
   }

@MappedMethod
   public Matrix4f(Matrix4x3fc arg0) {
      super(new org.joml.Matrix4f(arg0));
   }
}
