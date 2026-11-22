package org.mtr.mapping.holder;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class PlayerEntity extends HolderBase<Player> {
   public PlayerEntity(Player data) {
      super(data);
   }

   @MappedMethod
   public static PlayerEntity cast(HolderBase<?> data) {
      return new PlayerEntity((Player)data.data);
   }

   @MappedMethod
   public static boolean isInstance(@Nullable HolderBase<?> data) {
      return data != null && data.data instanceof Player;
   }

   @MappedMethod
   public final double getY() {
      return ((Player)this.data).getY();
   }

   @MappedMethod
   public double getY(double d) {
      return ((Player)this.data).getY(d);
   }

   @MappedMethod
   public final double getX() {
      return ((Player)this.data).getX();
   }

   @MappedMethod
   public double getX(double d) {
      return ((Player)this.data).getX(d);
   }

   @MappedMethod
   public double getZ(double d) {
      return ((Player)this.data).getZ(d);
   }

   @MappedMethod
   public final double getZ() {
      return ((Player)this.data).getZ();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getOffhandItem() {
      return new ItemStack(((Player)this.data).getOffhandItem());
   }

   @Nonnull
   @MappedMethod
   public UUID getUUID() {
      return ((Player)this.data).getUUID();
   }

   @Nonnull
   @MappedMethod
   public String getStringUUID() {
      return ((Player)this.data).getStringUUID();
   }

   @Nonnull
   @MappedMethod
   public GameProfile getGameProfile() {
      return ((Player)this.data).getGameProfile();
   }

   @MappedMethod
   public boolean startRiding(Entity entity, boolean bl, boolean bl2) {
      return ((Player)this.data).startRiding((net.minecraft.world.entity.Entity)entity.data, bl, bl2);
   }

   @MappedMethod
   public final boolean startRiding(Entity entity) {
      return ((Player)this.data).startRiding((net.minecraft.world.entity.Entity)entity.data);
   }

   @MappedMethod
   public float getYRot(float f) {
      return ((Player)this.data).getYRot(f);
   }

   @MappedMethod
   public float getYRot() {
      return ((Player)this.data).getYRot();
   }

   @MappedMethod
   public void setHealth(float f) {
      ((Player)this.data).setHealth(f);
   }

   @Nonnull
   @MappedMethod
   public ItemStack getMainHandItem() {
      return new ItemStack(((Player)this.data).getMainHandItem());
   }

   @Nonnull
   @MappedMethod
   public BlockPos blockPosition() {
      return new BlockPos(((Player)this.data).blockPosition());
   }

   @Nonnull
   @MappedMethod
   public Text getDisplayName() {
      return new Text(((Player)this.data).getDisplayName());
   }

   @MappedMethod
   public void playSound(SoundEvent soundEvent) {
      ((Player)this.data).playSound((net.minecraft.sounds.SoundEvent)soundEvent.data);
   }

   @MappedMethod
   public boolean isShiftKeyDown() {
      return ((Player)this.data).isShiftKeyDown();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getUseItem() {
      return new ItemStack(((Player)this.data).getUseItem());
   }

   @MappedMethod
   public boolean isHolding(Predicate<net.minecraft.world.item.ItemStack> predicate) {
      return ((Player)this.data).isHolding(predicate);
   }

   @MappedMethod
   public boolean isHolding(Item item) {
      return ((Player)this.data).isHolding((net.minecraft.world.item.Item)item.data);
   }

   @MappedMethod
   public boolean isCreative() {
      return ((Player)this.data).isCreative();
   }

   @MappedMethod
   public void setItemInHand(Hand interactionHand, ItemStack itemStack) {
      ((Player)this.data).setItemInHand(interactionHand.data, (net.minecraft.world.item.ItemStack)itemStack.data);
   }

   @Nonnull
   @MappedMethod
   public ItemStack getItemInHand(Hand interactionHand) {
      return new ItemStack(((Player)this.data).getItemInHand(interactionHand.data));
   }

   @MappedMethod
   public void displayClientMessage(Text component, boolean bl) {
      if (bl) ((Player)this.data).sendOverlayMessage((Component)component.data); else ((Player)this.data).sendSystemMessage((Component)component.data);
   }

   @MappedMethod
   public void swing(Hand interactionHand) {
      ((Player)this.data).swing(interactionHand.data, net.minecraft.world.item.component.SwingAnimation.DEFAULT, false);
   }

   @MappedMethod
   public void swing(Hand interactionHand, boolean bl) {
      ((Player)this.data).swing(interactionHand.data, net.minecraft.world.item.component.SwingAnimation.DEFAULT, bl);
   }

   @MappedMethod
   public final float getMaxHealth() {
      return ((Player)this.data).getMaxHealth();
   }

   @Nonnull
   @MappedMethod
   public World level() {
      return new World(((Player)this.data).level());
   }

   @MappedMethod
   public void playSound(SoundEvent soundEvent, float f, float g) {
      ((Player)this.data).playSound((net.minecraft.sounds.SoundEvent)soundEvent.data, f, g);
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

   @Nonnull
   @MappedMethod
   public String getUuidAsString() {
      return this.getStringUUID();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getStackInHand(Hand hand) {
      return this.getItemInHand(hand);
   }

   @MappedMethod
   public void setStackInHand(Hand hand, ItemStack stack) {
      this.setItemInHand(hand, stack);
   }

   @MappedMethod
   public void swingHand(Hand hand) {
      this.swing(hand);
   }

   @MappedMethod
   public void swingHand(Hand hand, boolean fromServer) {
      this.swing(hand, fromServer);
   }

   @Nonnull
   @MappedMethod
   public ItemStack getActiveItem() {
      return this.getUseItem();
   }

   @Nullable
   @MappedMethod
   public MinecraftServer getServer() {
      return this.level().getServer();
   }

   @MappedMethod
   public void playSound(SoundEvent sound, SoundCategory category, float volume, float pitch) {
      this.playSound(sound, volume, pitch);
   }
}
