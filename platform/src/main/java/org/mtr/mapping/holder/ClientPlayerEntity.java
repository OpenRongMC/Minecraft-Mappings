package org.mtr.mapping.holder;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;
@ParametersAreNonnullByDefault
public final class ClientPlayerEntity extends HolderBase<LocalPlayer> {
   public ClientPlayerEntity(LocalPlayer data) {
      super(data);
   }

   @MappedMethod
   public static boolean isInstance(@Nullable HolderBase<?> data) {
      return data != null && data.data instanceof LocalPlayer;
   }

   @MappedMethod
   public void setDeltaMovement(double d, double e, double f) {
      ((LocalPlayer)this.data).setDeltaMovement(d, e, f);
   }

   @MappedMethod
   public void setDeltaMovement(Vector3d vec3) {
      ((LocalPlayer)this.data).setDeltaMovement((Vec3)vec3.data);
   }

   @MappedMethod
   public boolean isSprinting() {
      return ((LocalPlayer)this.data).isSprinting();
   }

   @MappedMethod
   public final double getX() {
      return ((LocalPlayer)this.data).getX();
   }

   @MappedMethod
   public double getX(double d) {
      return ((LocalPlayer)this.data).getX(d);
   }

   @MappedMethod
   public double getZ(double d) {
      return ((LocalPlayer)this.data).getZ(d);
   }

   @MappedMethod
   public final double getZ() {
      return ((LocalPlayer)this.data).getZ();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getDeltaMovement() {
      return new Vector3d(((LocalPlayer)this.data).getDeltaMovement());
   }

   @MappedMethod
   public boolean isHolding(Predicate<net.minecraft.world.item.ItemStack> predicate) {
      return ((LocalPlayer)this.data).isHolding(predicate);
   }

   @MappedMethod
   public boolean isHolding(Item item) {
      return ((LocalPlayer)this.data).isHolding((net.minecraft.world.item.Item)item.data);
   }

   @Nonnull
   @MappedMethod
   public ItemStack getOffhandItem() {
      return new ItemStack(((LocalPlayer)this.data).getOffhandItem());
   }

   @MappedMethod
   public void setXRot(float f) {
      ((LocalPlayer)this.data).setXRot(f);
   }

   @Nonnull
   @MappedMethod
   public UUID getUUID() {
      return ((LocalPlayer)this.data).getUUID();
   }

   @Nonnull
   @MappedMethod
   public final Vector3d getEyePosition(float f) {
      return new Vector3d(((LocalPlayer)this.data).getEyePosition(f));
   }

   @Nonnull
   @MappedMethod
   public final Vector3d getEyePosition() {
      return new Vector3d(((LocalPlayer)this.data).getEyePosition());
   }

   @Nonnull
   @MappedMethod
   public ItemStack getItemInHand(Hand interactionHand) {
      return new ItemStack(((LocalPlayer)this.data).getItemInHand(interactionHand.data));
   }

   @MappedMethod
   public boolean isSpectator() {
      return ((LocalPlayer)this.data).isSpectator();
   }

   @MappedMethod
   public boolean isShiftKeyDown() {
      return ((LocalPlayer)this.data).isShiftKeyDown();
   }

   @MappedMethod
   public void playSound(SoundEvent soundEvent, float f, float g) {
      ((LocalPlayer)this.data).playSound((net.minecraft.sounds.SoundEvent)soundEvent.data, f, g);
   }

   @Nonnull
   @MappedMethod
   public Hand getUsedItemHand() {
      return Hand.convert(((LocalPlayer)this.data).getUsedItemHand());
   }

   @MappedMethod
   public float getYRot(float f) {
      return ((LocalPlayer)this.data).getYRot(f);
   }

   @MappedMethod
   public float getYRot() {
      return ((LocalPlayer)this.data).getYRot();
   }

   @MappedMethod
   public final void setPos(Vector3d vec3) {
      ((LocalPlayer)this.data).setPos((Vec3)vec3.data);
   }

   @MappedMethod
   public void setPos(double d, double e, double f) {
      ((LocalPlayer)this.data).setPos(d, e, f);
   }

   @MappedMethod
   public void setYRot(float f) {
      ((LocalPlayer)this.data).setYRot(f);
   }

   @MappedMethod
   public void setSpeed(float f) {
      ((LocalPlayer)this.data).setSpeed(f);
   }

   @Nonnull
   @MappedMethod
   public ItemStack getMainHandItem() {
      return new ItemStack(((LocalPlayer)this.data).getMainHandItem());
   }

   @Nonnull
   @MappedMethod
   public BlockPos blockPosition() {
      return new BlockPos(((LocalPlayer)this.data).blockPosition());
   }

   @MappedMethod
   public void playSound(SoundEvent soundEvent) {
      ((LocalPlayer)this.data).playSound((net.minecraft.sounds.SoundEvent)soundEvent.data);
   }

   @MappedMethod
   public float getXRot(float f) {
      return ((LocalPlayer)this.data).getXRot(f);
   }

   @MappedMethod
   public float getXRot() {
      return ((LocalPlayer)this.data).getXRot();
   }

   @MappedMethod
   public void displayClientMessage(Text component, boolean bl) {
      if (bl) ((LocalPlayer)this.data).sendOverlayMessage((Component)component.data); else ((LocalPlayer)this.data).sendSystemMessage((Component)component.data);
   }

   @MappedMethod
   public void setFallDistanceMapped(double newData) {
      ((LocalPlayer)this.data).fallDistance = newData;
   }

   @MappedMethod
   public void setYRotOMapped(float newData) {
      ((LocalPlayer)this.data).yRotO = newData;
   }

   @MappedMethod
   public float getZzaMapped() {
      return ((LocalPlayer)this.data).zza;
   }

   @MappedMethod
   public void setXRotOMapped(float newData) {
      ((LocalPlayer)this.data).xRotO = newData;
   }

   @MappedMethod
   public float getXxaMapped() {
      return ((LocalPlayer)this.data).xxa;
   }

   @MappedMethod
   public void sendMessage(Text message, boolean actionBar) {
      this.displayClientMessage(message, actionBar);
   }

   @Nonnull
   @MappedMethod
   public UUID getUuid() {
      return this.getUUID();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getMainHandStack() {
      return this.getMainHandItem();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getOffHandStack() {
      return this.getOffhandItem();
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos() {
      return this.blockPosition();
   }

   @MappedMethod
   public boolean isSneaking() {
      return this.isShiftKeyDown();
   }

   @MappedMethod
   public float getSidewaysSpeedMapped() {
      return this.getXxaMapped();
   }

   @MappedMethod
   public float getForwardSpeedMapped() {
      return this.getZzaMapped();
   }

   @MappedMethod
   public void setVelocity(double x, double y, double z) {
      this.setDeltaMovement(x, y, z);
   }

   @MappedMethod
   public void setVelocity(Vector3d v) {
      this.setDeltaMovement(v);
   }

   @MappedMethod
   public void updatePosition(double x, double y, double z) {
      this.setPos(x, y, z);
   }

   @MappedMethod
   public void setMovementSpeed(float speed) {
      this.setSpeed(speed);
   }

   @Nonnull
   @MappedMethod
   public Hand getActiveHand() {
      return this.getUsedItemHand();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos() {
      return new Vector3d(((LocalPlayer)this.data).position());
   }

   @Nonnull
   @MappedMethod
   public ItemStack getStackInHand(Hand hand) {
      return this.getItemInHand(hand);
   }


}
