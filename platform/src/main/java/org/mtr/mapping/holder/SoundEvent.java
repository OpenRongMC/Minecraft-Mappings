package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SoundEvent extends HolderBase<net.minecraft.sounds.SoundEvent> {
   public SoundEvent(net.minecraft.sounds.SoundEvent data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Identifier location() {
      return new Identifier(((net.minecraft.sounds.SoundEvent)this.data).location());
   }

   @Nonnull
   @MappedMethod
   public static SoundEvent createVariableRangeEvent(Identifier identifier) {
      return new SoundEvent(net.minecraft.sounds.SoundEvent.createVariableRangeEvent((net.minecraft.resources.Identifier)identifier.data));
   }

   @Nonnull
   @MappedMethod
   public Identifier getId() {
      return this.location();
   }


}
