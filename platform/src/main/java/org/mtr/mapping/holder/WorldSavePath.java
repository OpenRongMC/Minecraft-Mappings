package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.storage.LevelResource;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class WorldSavePath extends HolderBase<LevelResource> {
   public WorldSavePath(LevelResource data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static WorldSavePath getRootMapped() {
      return new WorldSavePath(LevelResource.ROOT);
   }

}
