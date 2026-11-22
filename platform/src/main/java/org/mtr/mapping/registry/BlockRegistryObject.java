package org.mtr.mapping.registry;

import java.util.function.Consumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.tool.RegistryObject;

public final class BlockRegistryObject extends RegistryObject<Block> {
   private final Block block;

   BlockRegistryObject(Block block) {
      this.block = block;
   }

   @MappedMethod
   public Block get() {
      return this.block;
   }

   @MappedMethod
   public boolean isPresent() {
      return true;
   }

   @MappedMethod
   public void ifPresent(Consumer<Block> consumer) {
      consumer.accept(this.block);
   }
}
