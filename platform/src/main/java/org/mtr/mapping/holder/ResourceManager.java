package org.mtr.mapping.holder;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ResourceManager extends HolderBase<net.minecraft.server.packs.resources.ResourceManager> {
   public ResourceManager(net.minecraft.server.packs.resources.ResourceManager data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Optional<net.minecraft.server.packs.resources.Resource> getResource(Identifier identifier) {
      return ((net.minecraft.server.packs.resources.ResourceManager)this.data).getResource((net.minecraft.resources.Identifier)identifier.data);
   }

   @Nonnull
   @MappedMethod
   public List<net.minecraft.server.packs.resources.Resource> getResourceStack(Identifier identifier) {
      return ((net.minecraft.server.packs.resources.ResourceManager)this.data).getResourceStack((net.minecraft.resources.Identifier)identifier.data);
   }

   @Nonnull
   @MappedMethod
   public Map<net.minecraft.resources.Identifier, net.minecraft.server.packs.resources.Resource> listResources(
      String string, Predicate<net.minecraft.resources.Identifier> predicate
   ) {
      return ((net.minecraft.server.packs.resources.ResourceManager)this.data).listResources(string, predicate::test);
   }

}
