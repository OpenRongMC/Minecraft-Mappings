package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class EntityAbstractMapping extends net.minecraft.world.entity.Entity {

   @MappedMethod
   public int getId2() {
      return super.getId();
   }

   @Deprecated
   public final int getId() {
      return this.getId2();
   }

   @MappedMethod
   protected abstract void defineSynchedData2(Builder var1);

   @Deprecated
   protected final void defineSynchedData(Builder builder) {
      this.defineSynchedData2(builder);
   }

   @MappedMethod
   protected abstract void addAdditionalSaveData2(ValueOutput var1);

   @Deprecated
   protected final void addAdditionalSaveData(ValueOutput valueOutput) {
      this.addAdditionalSaveData2(valueOutput);
   }

   @MappedMethod
   protected abstract void readAdditionalSaveData2(ValueInput var1);

   @Deprecated
   protected final void readAdditionalSaveData(ValueInput valueInput) {
      this.readAdditionalSaveData2(valueInput);
   }

   @MappedMethod
   public void setId2(int i) {
      super.setId(i);
   }

   @Deprecated
   public final void setId(int i) {
      this.setId2(i);
   }

   @Nonnull
   @MappedMethod
   public final Vector3d getEyePosition2(float f) {
      return new Vector3d(super.getEyePosition(f));
   }

   @Nonnull
   @MappedMethod
   public final Vector3d getEyePosition2() {
      return new Vector3d(super.getEyePosition());
   }

   @MappedMethod
   public void kill2(ServerWorld serverLevel) {
      super.kill((ServerLevel)serverLevel.data);
   }

   @Deprecated
   public final void kill(ServerLevel serverLevel) {
      this.kill2(new ServerWorld(serverLevel));
   }

   @MappedMethod
   public abstract boolean hurtServer2(ServerWorld var1, DamageSource var2, float var3);

   @Deprecated
   public final boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f) {
      return this.hurtServer2(new ServerWorld(serverLevel), damageSource, f);
   }

   @MappedMethod
   public final void discard2() {
      super.discard();
   }

   @MappedMethod
   public void setInvisible2(boolean bl) {
      super.setInvisible(bl);
   }

   @Deprecated
   public final void setInvisible(boolean bl) {
      this.setInvisible2(bl);
   }

   @MappedMethod
   public void setNoGravity2(boolean bl) {
      super.setNoGravity(bl);
   }

   @Deprecated
   public final void setNoGravity(boolean bl) {
      this.setNoGravity2(bl);
   }

   @MappedMethod
   public float getYRot2(float f) {
      return super.getYRot(f);
   }

   @Deprecated
   public final float getYRot(float f) {
      return this.getYRot2(f);
   }

   @MappedMethod
   public float getYRot2() {
      return super.getYRot();
   }

   @Deprecated
   public final float getYRot() {
      return this.getYRot2();
   }

   @MappedMethod
   public final void setPos2(Vector3d vec3) {
      super.setPos((Vec3)vec3.data);
   }

   @MappedMethod
   public void setPos2(double d, double e, double f) {
      super.setPos(d, e, f);
   }

   @Deprecated
   public final void setPos(double d, double e, double f) {
      this.setPos2(d, e, f);
   }

   @MappedMethod
   protected void removePassenger2(Entity entity) {
      super.removePassenger((net.minecraft.world.entity.Entity)entity.data);
   }

   @Deprecated
   protected final void removePassenger(net.minecraft.world.entity.Entity entity) {
      this.removePassenger2(new Entity(entity));
   }

   @Nonnull
   @MappedMethod
   public Vector3d position2() {
      return new Vector3d(super.position());
   }

   @Deprecated
   public final Vec3 position() {
      Vector3d tempData = this.position2();
      return tempData == null ? null : (Vec3)tempData.data;
   }

   @Nonnull
   @MappedMethod
   public World level2() {
      return new World(super.level());
   }

   @Deprecated
   public final Level level() {
      World tempData = this.level2();
      return tempData == null ? null : (Level)tempData.data;
   }

   @MappedMethod
   public void tick2() {
      super.tick();
   }

   @Deprecated
   public final void tick() {
      this.tick2();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos2() {
      return this.position2();
   }

   @Nullable
   @MappedMethod
   public World getEntityWorld2() {
      return this.level2();
   }

   @MappedMethod
   public void kill2() {
      World w = this.level2();
      if (w != null && w.data instanceof ServerLevel sl) {
         this.kill2(new ServerWorld(sl));
      } else {
         this.discard2();
      }
   }

@MappedMethod
   public EntityAbstractMapping(EntityType<?> entityType, World level) {
      super((net.minecraft.world.entity.EntityType)entityType.data, (Level)level.data);
   }
}
