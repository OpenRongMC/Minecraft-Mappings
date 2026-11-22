package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.entity.Pose;
@ParametersAreNonnullByDefault
public enum EntityPose {
   STANDING(Pose.STANDING),
   FALL_FLYING(Pose.FALL_FLYING),
   SLEEPING(Pose.SLEEPING),
   SWIMMING(Pose.SWIMMING),
   SPIN_ATTACK(Pose.SPIN_ATTACK),
   CROUCHING(Pose.CROUCHING),
   LONG_JUMPING(Pose.LONG_JUMPING),
   DYING(Pose.DYING),
   CROAKING(Pose.CROAKING),
   USING_TONGUE(Pose.USING_TONGUE),
   SITTING(Pose.SITTING),
   ROARING(Pose.ROARING),
   SNIFFING(Pose.SNIFFING),
   EMERGING(Pose.EMERGING),
   DIGGING(Pose.DIGGING),
   SLIDING(Pose.SLIDING),
   SHOOTING(Pose.SHOOTING),
   INHALING(Pose.INHALING);

   public final Pose data;

   private EntityPose(Pose data) {
      this.data = data;
   }

   public static EntityPose convert(@Nullable Pose data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
