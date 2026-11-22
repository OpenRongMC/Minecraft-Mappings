package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class EntityType<T extends net.minecraft.world.entity.Entity> extends HolderBase<net.minecraft.world.entity.EntityType<T>> {
   public EntityType(net.minecraft.world.entity.EntityType<T> data) {
      super(data);
   }


}
