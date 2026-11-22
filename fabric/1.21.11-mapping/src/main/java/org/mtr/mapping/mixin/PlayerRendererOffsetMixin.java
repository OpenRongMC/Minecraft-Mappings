package org.mtr.mapping.mixin;

import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.mapper.EntityHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AvatarRenderer.class})
public abstract class PlayerRendererOffsetMixin {
   @Inject(
      method = {"getRenderOffset(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)Lnet/minecraft/world/phys/Vec3;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void getRenderOffset(AvatarRenderState state, CallbackInfoReturnable<Vec3> callbackInfoReturnable) {
      if (state.nameTag != null && EntityHelper.HIDDEN_PLAYERS.stream().anyMatch(uuid -> state.nameTag.getString().contains(uuid.toString()))) {
         callbackInfoReturnable.setReturnValue(new Vec3(0.0, -1000.0, 0.0));
      }
   }
}
