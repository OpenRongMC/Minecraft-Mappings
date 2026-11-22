package org.mtr.mapping.holder;


import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class TextureManager extends HolderBase<net.minecraft.client.renderer.texture.TextureManager> {
   public TextureManager(net.minecraft.client.renderer.texture.TextureManager data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public AbstractTexture getTexture(Identifier identifier) {
      return new AbstractTexture(
         ((net.minecraft.client.renderer.texture.TextureManager)this.data).getTexture((net.minecraft.resources.Identifier)identifier.data)
      );
   }

   @MappedMethod
   public void register(Identifier identifier, AbstractTexture abstractTexture) {
      ((net.minecraft.client.renderer.texture.TextureManager)this.data)
         .register((net.minecraft.resources.Identifier)identifier.data, (net.minecraft.client.renderer.texture.AbstractTexture)abstractTexture.data);
   }

   @MappedMethod
   public void release(Identifier identifier) {
      ((net.minecraft.client.renderer.texture.TextureManager)this.data).release((net.minecraft.resources.Identifier)identifier.data);
   }

   @MappedMethod
   public void destroyTexture(Identifier id) {
      this.release(id);
   }

   @MappedMethod
   public void registerTexture(Identifier id, AbstractTexture texture) {
      this.register(id, texture);
   }

   @Nonnull
   @MappedMethod
   public Identifier registerDynamicTexture(String name, NativeImageBackedTexture texture) {
      Identifier id = new Identifier("minecraft", "dynamic/" + name + "_" + UUID.randomUUID().toString().replace("-", ""));
      ((net.minecraft.client.renderer.texture.TextureManager)this.data)
         .register((net.minecraft.resources.Identifier)id.data, (net.minecraft.client.renderer.texture.AbstractTexture)texture.data);
      return id;
   }

@MappedMethod
   public TextureManager(ResourceManager resourceManager) {
      super(new net.minecraft.client.renderer.texture.TextureManager((net.minecraft.server.packs.resources.ResourceManager)resourceManager.data));
   }
}
