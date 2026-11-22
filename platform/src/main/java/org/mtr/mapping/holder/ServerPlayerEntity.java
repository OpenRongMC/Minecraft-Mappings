package org.mtr.mapping.holder;


import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ServerPlayerEntity extends HolderBase<ServerPlayer> {
   public ServerPlayerEntity(ServerPlayer data) {
      super(data);
   }

   @MappedMethod
   public static ServerPlayerEntity cast(HolderBase<?> data) {
      return new ServerPlayerEntity((ServerPlayer)data.data);
   }

   @MappedMethod
   public static boolean isInstance(@Nullable HolderBase<?> data) {
      return data != null && data.data instanceof ServerPlayer;
   }

   @Nonnull
   @MappedMethod
   public UUID getUUID() {
      return ((ServerPlayer)this.data).getUUID();
   }

   @MappedMethod
   public boolean addItem(ItemStack itemStack) {
      return ((ServerPlayer)this.data).addItem((net.minecraft.world.item.ItemStack)itemStack.data);
   }

   @MappedMethod
   public void displayClientMessage(Text component, boolean bl) {
      if (bl) ((ServerPlayer)this.data).sendOverlayMessage((Component)component.data); else ((ServerPlayer)this.data).sendSystemMessage((Component)component.data);
   }

   @Nonnull
   @MappedMethod
   public BlockPos blockPosition() {
      return new BlockPos(((ServerPlayer)this.data).blockPosition());
   }

   @MappedMethod
   public void playSound(SoundEvent soundEvent) {
      ((ServerPlayer)this.data).playSound((net.minecraft.sounds.SoundEvent)soundEvent.data);
   }

   @Nonnull
   @MappedMethod
   public ServerWorld level() {
      return new ServerWorld(((ServerPlayer)this.data).level());
   }

   @Nonnull
   @MappedMethod
   public Text getName() {
      return new Text(((ServerPlayer)this.data).getName());
   }

   @MappedMethod
   public void setNoGravity(boolean bl) {
      ((ServerPlayer)this.data).setNoGravity(bl);
   }

   @MappedMethod
   public void playSound(SoundEvent soundEvent, float f, float g) {
      ((ServerPlayer)this.data).playSound((net.minecraft.sounds.SoundEvent)soundEvent.data, f, g);
   }

   @MappedMethod
   public void setFallDistanceMapped(double newData) {
      ((ServerPlayer)this.data).fallDistance = newData;
   }

   @MappedMethod
   public void setNoPhysicsMapped(boolean newData) {
      ((ServerPlayer)this.data).noPhysics = newData;
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
   public World getEntityWorld() {
      return new World((Level)this.level().data);
   }

   @Nonnull
   @MappedMethod
   public ServerWorld getServerWorld() {
      return this.level();
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos() {
      return this.blockPosition();
   }

   @MappedMethod
   public void setNoClipMapped(boolean v) {
      this.setNoPhysicsMapped(v);
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos() {
      return new Vector3d(((ServerPlayer)this.data).position());
   }


}
