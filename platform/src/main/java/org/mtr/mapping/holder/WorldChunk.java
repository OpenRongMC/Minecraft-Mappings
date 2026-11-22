package org.mtr.mapping.holder;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.chunk.LevelChunk;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class WorldChunk extends HolderBase<LevelChunk> {
   public WorldChunk(LevelChunk data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Map<net.minecraft.core.BlockPos, net.minecraft.world.level.block.entity.BlockEntity> getBlockEntities() {
      return ((LevelChunk)this.data).getBlockEntities();
   }






}
