package org.mtr.mapping.registry;

import java.util.function.Consumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.tool.RegistryObject;

public final class ItemRegistryObject extends RegistryObject<Item> {
   private final Item item;

   ItemRegistryObject(Item item) {
      this.item = item;
   }

   @MappedMethod
   public Item get() {
      return this.item;
   }

   @MappedMethod
   public boolean isPresent() {
      return true;
   }

   @MappedMethod
   public void ifPresent(Consumer<Item> consumer) {
      consumer.accept(this.item);
   }
}
