package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.CameraType;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class GameOptions extends HolderBase<Options> {
   public GameOptions(Options data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public OptionInstance<Integer> fov() {
      return ((Options)this.data).fov();
   }

   @MappedMethod
   public int getEffectiveRenderDistance() {
      return ((Options)this.data).getEffectiveRenderDistance();
   }

   @Nonnull
   @MappedMethod
   public CameraType getCameraType() {
      return ((Options)this.data).getCameraType();
   }

   @Nonnull
   @MappedMethod
   public KeyBinding getKeyShiftMapped() {
      return new KeyBinding(((Options)this.data).keyShift);
   }

   @Nonnull
   @MappedMethod
   public KeyBinding getKeyUseMapped() {
      return new KeyBinding(((Options)this.data).keyUse);
   }

   @Nonnull
   @MappedMethod
   public KeyBinding getKeySneakMapped() {
      return this.getKeyShiftMapped();
   }


}
