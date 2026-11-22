package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Util extends HolderBase<net.minecraft.util.Util> {
   public Util(net.minecraft.util.Util data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static OperatingSystem getPlatform() {
      return OperatingSystem.convert(net.minecraft.util.Util.getPlatform());
   }

   @Nonnull
   @MappedMethod
   public static OperatingSystem getOperatingSystem() {
      return getPlatform();
   }


}
