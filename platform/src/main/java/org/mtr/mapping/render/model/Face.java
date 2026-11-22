package org.mtr.mapping.render.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public final class Face {
   public final int[] vertices;

   public Face(int[] vertices) {
      this.vertices = vertices;
   }

   public Face(Face face) {
      this.vertices = Arrays.copyOf(face.vertices, face.vertices.length);
   }

   public static List<Face> triangulate(int[] vertices) {
      List<Face> result = new ArrayList<>();
      if (vertices.length > 3) {
         for (int i = 2; i < vertices.length; i++) {
            result.add(new Face(new int[]{vertices[0], vertices[i - 1], vertices[i]}));
         }
      } else {
         result.add(new Face(new int[]{vertices[0], vertices[1], vertices[2]}));
      }

      return result;
   }

   public void flip() {
      ArrayUtils.reverse(this.vertices);
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         return !(object instanceof Face) ? false : Arrays.equals(this.vertices, ((Face)object).vertices);
      }
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(this.vertices);
   }
}
