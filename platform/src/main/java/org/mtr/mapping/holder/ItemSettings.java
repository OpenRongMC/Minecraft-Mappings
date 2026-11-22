package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item.Properties;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.registry.Registry;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ItemSettings extends HolderBase<Properties> {
   public ItemSettings(Properties data) {
      super(applyPendingItemId(data));
   }

   private static Properties applyPendingItemId(Properties data) {
      Identifier id = Registry.PENDING_ITEM_ID.get();
      if (id != null) {
         data = data.setId(ResourceKey.create(Registries.ITEM, (net.minecraft.resources.Identifier)id.data));
         String description = Registry.pendingItemDescription();
         if (description != null) {
            data = data.overrideDescription(description);
         }
      }

      return data;
   }

   @Nonnull
   @MappedMethod
   public ItemSettings useBlockDescriptionPrefix() {
      return new ItemSettings(((Properties)this.data).useBlockDescriptionPrefix());
   }

   @Nonnull
   @MappedMethod
   public ItemSettings stacksTo(int i) {
      return new ItemSettings(((Properties)this.data).stacksTo(i));
   }

   @Nonnull
   @MappedMethod
   public ItemSettings maxCount(int count) {
      return this.stacksTo(count);
   }

@MappedMethod
   public ItemSettings() {
      this(new Properties());
   }
}
