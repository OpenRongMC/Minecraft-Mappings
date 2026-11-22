package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Model<S> extends HolderBase<net.minecraft.client.model.Model<S>> {
   public Model(net.minecraft.client.model.Model<S> data) {
      super(data);
   }

}
