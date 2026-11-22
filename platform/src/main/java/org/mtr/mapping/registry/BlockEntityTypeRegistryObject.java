package org.mtr.mapping.registry;

import java.util.function.Consumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.tool.RegistryObject;

public final class BlockEntityTypeRegistryObject<T extends BlockEntityExtension> extends RegistryObject<BlockEntityType<T>> {
   private final BlockEntityType<T> blockEntityType;

   BlockEntityTypeRegistryObject(BlockEntityType<T> blockEntityType) {
      this.blockEntityType = blockEntityType;
   }

   @MappedMethod
   public BlockEntityType<T> get() {
      return this.blockEntityType;
   }

   @MappedMethod
   public boolean isPresent() {
      return true;
   }

   @MappedMethod
   public void ifPresent(Consumer<BlockEntityType<T>> consumer) {
      consumer.accept(this.blockEntityType);
   }
}
