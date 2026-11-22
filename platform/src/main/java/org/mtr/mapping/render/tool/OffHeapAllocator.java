package org.mtr.mapping.render.tool;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.MemoryUtil.MemoryAllocator;

public final class OffHeapAllocator {
   private static final MemoryAllocator ALLOCATOR = MemoryUtil.getAllocator(false);

   public static ByteBuffer allocate(int size) {
      long pointer = ALLOCATOR.malloc(size);
      if (pointer == 0L) {
         throw new OutOfMemoryError();
      } else {
         return MemoryUtil.memByteBuffer(pointer, size);
      }
   }

   public static void free(ByteBuffer byteBuffer) {
      ALLOCATOR.free(MemoryUtil.memAddress0(byteBuffer));
   }
}
