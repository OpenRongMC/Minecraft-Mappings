package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Identifier extends HolderBase<net.minecraft.resources.Identifier> {
   public Identifier(net.minecraft.resources.Identifier data) {
      super(data);
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((net.minecraft.resources.Identifier)this.data).equals(((HolderBase)data).data);
   }

   @Nonnull
   @MappedMethod
   public String getPath() {
      return ((net.minecraft.resources.Identifier)this.data).getPath();
   }

   @Nonnull
   @MappedMethod
   public String getNamespace() {
      return ((net.minecraft.resources.Identifier)this.data).getNamespace();
   }

   @Nonnull
   @MappedMethod
   public static Identifier fromNamespaceAndPath(String string, String string2) {
      return new Identifier(net.minecraft.resources.Identifier.fromNamespaceAndPath(string, string2));
   }

   @Nonnull
   @MappedMethod
   public static Identifier tryParse(String string) {
      return new Identifier(net.minecraft.resources.Identifier.tryParse(string));
   }

   @Nonnull
   @MappedMethod
   public static Identifier parse(String string) {
      return new Identifier(net.minecraft.resources.Identifier.parse(string));
   }

@MappedMethod
   public Identifier(String id) {
      super(net.minecraft.resources.Identifier.parse(id));
   }

@MappedMethod
   public Identifier(String namespace, String path) {
      super(net.minecraft.resources.Identifier.fromNamespaceAndPath(namespace, path));
   }
}
