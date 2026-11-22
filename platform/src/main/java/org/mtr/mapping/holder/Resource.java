package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Resource extends HolderBase<net.minecraft.server.packs.resources.Resource> {
   public Resource(net.minecraft.server.packs.resources.Resource data) {
      super(data);
   }




}
