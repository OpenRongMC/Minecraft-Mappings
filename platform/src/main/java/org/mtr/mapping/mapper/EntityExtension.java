package org.mtr.mapping.mapper;

import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.holder.Vector3d;
import org.mtr.mapping.holder.World;

public abstract class EntityExtension extends EntityAbstractMapping {
   @MappedMethod
   public EntityExtension(EntityType<?> type, World world) {
      super(type, world);
   }

   @Deprecated
   @Override
   protected void defineSynchedData2(Builder builder) {
      this.initDataTracker2();
   }

   @MappedMethod
   protected void initDataTracker2() {
   }

   @Deprecated
   @Override
   protected void readAdditionalSaveData2(ValueInput valueInput) {
   }

   @Deprecated
   @Override
   protected void addAdditionalSaveData2(ValueOutput valueOutput) {
   }

   @MappedMethod
   public void setPosition2(double x, double y, double z) {
      super.setPos2(x, y, z);
   }

   @MappedMethod
   @Override
   public boolean hurtServer2(ServerWorld serverWorld, DamageSource damageSource, float amount) {
      return false;
   }

   @MappedMethod
   public Vector3d getCameraPosVec2(float tickDelta) {
      return this.getEyePosition2(tickDelta);
   }
}
