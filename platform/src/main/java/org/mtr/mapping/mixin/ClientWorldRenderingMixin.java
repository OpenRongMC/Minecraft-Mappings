package org.mtr.mapping.mixin;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.EntityExtension;
import org.mtr.mapping.registry.RegistryClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientLevel.class})
public abstract class ClientWorldRenderingMixin {
   @Inject(
      method = {"entitiesForRendering"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void getEntities(CallbackInfoReturnable<Iterable<Entity>> callbackInfoReturnable) {
      if (RegistryClient.worldRenderingEntity != null) {
         EntityExtension entity = RegistryClient.worldRenderingEntity.apply(new World((ClientLevel)this));
         if (entity != null) {
            List<Entity> entities = Lists.newArrayList((Iterable)callbackInfoReturnable.getReturnValue());
            entities.add(entity);
            callbackInfoReturnable.setReturnValue(entities);
         }
      }
   }
}
