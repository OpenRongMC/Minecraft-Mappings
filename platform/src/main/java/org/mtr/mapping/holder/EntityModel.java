package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class EntityModel<T extends EntityRenderState> extends HolderBase<net.minecraft.client.model.EntityModel<T>> {
   public EntityModel(net.minecraft.client.model.EntityModel<T> data) {
      super(data);
   }

}
