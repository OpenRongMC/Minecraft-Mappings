package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.RenderBuffers;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BufferBuilderStorage extends HolderBase<RenderBuffers> {
   public BufferBuilderStorage(RenderBuffers data) {
      super(data);
   }

@MappedMethod
   public BufferBuilderStorage(int i) {
      super(new RenderBuffers(i));
   }
}
