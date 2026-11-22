package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BufferBuilder extends HolderBase<com.mojang.blaze3d.vertex.BufferBuilder> {
   public BufferBuilder(com.mojang.blaze3d.vertex.BufferBuilder data) {
      super(data);
   }


}
