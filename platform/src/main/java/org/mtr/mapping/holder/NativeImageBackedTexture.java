package org.mtr.mapping.holder;
import java.util.function.Supplier;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class NativeImageBackedTexture extends HolderBase<DynamicTexture> {
   public NativeImageBackedTexture(DynamicTexture data) {
      super(data);
   }

   @MappedMethod
   public void upload() {
      ((DynamicTexture)this.data).upload();
   }

   @Nonnull
   @MappedMethod
   public NativeImage getPixels() {
      return new NativeImage(((DynamicTexture)this.data).getPixels());
   }

   @MappedMethod
   public void close() {
      ((DynamicTexture)this.data).close();
   }

   @Nullable
   @MappedMethod
   public NativeImage getImage() {
      return this.getPixels();
   }





@MappedMethod
   public NativeImageBackedTexture(Supplier<String> supplier, NativeImage nativeImage) {
      super(new DynamicTexture(supplier, (com.mojang.blaze3d.platform.NativeImage)nativeImage.data));
   }

@MappedMethod
   public NativeImageBackedTexture(NativeImage image) {
      this(() -> "mtr", image);
   }
}
