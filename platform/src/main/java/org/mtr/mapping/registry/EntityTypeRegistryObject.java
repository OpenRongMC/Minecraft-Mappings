package org.mtr.mapping.registry;

import java.util.function.Consumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.mapper.EntityExtension;
import org.mtr.mapping.tool.RegistryObject;

public final class EntityTypeRegistryObject<T extends EntityExtension> extends RegistryObject<EntityType<T>> {
   private final EntityType<T> entityType;

   EntityTypeRegistryObject(EntityType<T> entityType) {
      this.entityType = entityType;
   }

   @MappedMethod
   public EntityType<T> get() {
      return this.entityType;
   }

   @MappedMethod
   public boolean isPresent() {
      return true;
   }

   @MappedMethod
   public void ifPresent(Consumer<EntityType<T>> consumer) {
      consumer.accept(this.entityType);
   }
}
