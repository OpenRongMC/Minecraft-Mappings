package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ShapeContext extends HolderBase<CollisionContext> {
   public ShapeContext(CollisionContext data) {
      super(data);
   }

}
