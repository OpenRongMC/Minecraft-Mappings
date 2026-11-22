package org.mtr.mapping.holder;


import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.joml.Vector3dc;
import org.joml.Vector3fc;
import org.joml.Vector3ic;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Vector3f extends HolderBase<org.joml.Vector3f> {
   public Vector3f(org.joml.Vector3f data) {
      super(data);
   }

   @MappedMethod
   public float getX() {
      return ((org.joml.Vector3f)this.data).x();
   }

   @MappedMethod
   public float getY() {
      return ((org.joml.Vector3f)this.data).y();
   }

   @MappedMethod
   public float getZ() {
      return ((org.joml.Vector3f)this.data).z();
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((org.joml.Vector3f)this.data).equals(((HolderBase)data).data);
   }

   @MappedMethod
   public boolean equals(float arg0, float arg1, float arg2) {
      return ((org.joml.Vector3f)this.data).equals(arg0, arg1, arg2);
   }

   @MappedMethod
   public boolean equals(Vector3fc arg0, float arg1) {
      return ((org.joml.Vector3f)this.data).equals(arg0, arg1);
   }

@MappedMethod
   public Vector3f(float[] arg0) {
      super(new org.joml.Vector3f(arg0));
   }

@MappedMethod
   public Vector3f(ByteBuffer arg0) {
      super(new org.joml.Vector3f(arg0));
   }







@MappedMethod
   public Vector3f(FloatBuffer arg0) {
      super(new org.joml.Vector3f(arg0));
   }

@MappedMethod
   public Vector3f(Vector3fc arg0) {
      super(new org.joml.Vector3f(arg0));
   }





@MappedMethod
   public Vector3f(float arg0) {
      super(new org.joml.Vector3f(arg0));
   }

@MappedMethod
   public Vector3f(float arg0, float arg1, float arg2) {
      super(new org.joml.Vector3f(arg0, arg1, arg2));
   }

@MappedMethod
   public Vector3f(Vector3dc arg0) {
      super(new org.joml.Vector3f(arg0));
   }

@MappedMethod
   public Vector3f(Vector3ic arg0) {
      super(new org.joml.Vector3f(arg0));
   }
}
