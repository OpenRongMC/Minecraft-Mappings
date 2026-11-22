package org.mtr.mapping.render.vertex;

import java.util.Objects;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.render.tool.Utilities;

public final class Vertex {
   public Vector3f position;
   public Vector3f normal;
   public float u;
   public float v;
   public int color;
   public int light;

   public Vertex() {
   }

   public Vertex(Vertex vertex) {
      this.position = Utilities.copy(vertex.position);
      this.normal = Utilities.copy(vertex.normal);
      this.u = vertex.u;
      this.v = vertex.v;
      this.color = vertex.color;
      this.light = vertex.light;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Vertex vertex = (Vertex)o;
         return Float.compare(vertex.u, this.u) == 0
            && Float.compare(vertex.v, this.v) == 0
            && this.position.equals(vertex.position)
            && this.normal.equals(vertex.normal);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.position, this.normal, this.u, this.v);
   }
}
