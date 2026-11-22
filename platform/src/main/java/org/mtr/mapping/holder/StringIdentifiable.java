package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.StringRepresentable;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public interface StringIdentifiable extends StringRepresentable {
   @Nonnull
   @MappedMethod
   String asString2();

   @Override default String getSerializedName() { return asString2(); }
}
