package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum HeightMapType {
   WORLD_SURFACE_WG(Types.WORLD_SURFACE_WG),
   WORLD_SURFACE(Types.WORLD_SURFACE),
   OCEAN_FLOOR_WG(Types.OCEAN_FLOOR_WG),
   OCEAN_FLOOR(Types.OCEAN_FLOOR),
   MOTION_BLOCKING(Types.MOTION_BLOCKING),
   MOTION_BLOCKING_NO_LEAVES(Types.MOTION_BLOCKING_NO_LEAVES);

   public final Types data;

   private HeightMapType(Types data) {
      this.data = data;
   }

   public static HeightMapType convert(@Nullable Types data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @Nonnull
   @MappedMethod
   public static HeightMapType getMotionBlockingMapped() {
      return convert(Types.MOTION_BLOCKING);
   }
}
