package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.registry.Registry;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockSettings extends HolderBase<Properties> {
   public BlockSettings(Properties data) {
      super(applyPendingBlockId(data));
   }

   private static Properties applyPendingBlockId(Properties data) {
      Identifier id = Registry.PENDING_BLOCK_ID.get();
      if (id != null) {
         data = data.setId(ResourceKey.create(Registries.BLOCK, (net.minecraft.resources.Identifier)id.data));
         String description = Registry.pendingBlockDescription();
         if (description != null) {
            data = data.overrideDescription(description);
         }
      }

      return data;
   }

   @Nonnull
   @MappedMethod
   public BlockSettings strength(float f, float g) {
      return new BlockSettings(((Properties)this.data).strength(f, g));
   }

   @Nonnull
   @MappedMethod
   public BlockSettings strength(float f) {
      return new BlockSettings(((Properties)this.data).strength(f));
   }

   @Nonnull
   @MappedMethod
   public BlockSettings noOcclusion() {
      return new BlockSettings(((Properties)this.data).noOcclusion());
   }

   @Nonnull
   @MappedMethod
   public BlockSettings instabreak() {
      return new BlockSettings(((Properties)this.data).instabreak());
   }

   @Nonnull
   @MappedMethod
   public BlockSettings noCollision() {
      return new BlockSettings(((Properties)this.data).noCollision());
   }

   @Nonnull
   @MappedMethod
   public BlockSettings nonOpaque() {
      return this.noOcclusion();
   }
   /** Entity-backed geometry must bypass the vanilla state-only shape cache. */
   public BlockSettings dynamicShape() { return new BlockSettings(((Properties)data).dynamicShape()); }
}
