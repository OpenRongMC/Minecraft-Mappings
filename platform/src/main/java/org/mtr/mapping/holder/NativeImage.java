package org.mtr.mapping.holder;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class NativeImage extends HolderBase<com.mojang.blaze3d.platform.NativeImage> {
   public NativeImage(com.mojang.blaze3d.platform.NativeImage data) {
      super(data);
   }

   @MappedMethod
   public void close() {
      ((com.mojang.blaze3d.platform.NativeImage)this.data).close();
   }

   @MappedMethod
   public void writeToFile(Path path) throws IOException {
      ((com.mojang.blaze3d.platform.NativeImage)this.data).writeToFile(path);
   }

   @MappedMethod
   public void writeToFile(File file) throws IOException {
      ((com.mojang.blaze3d.platform.NativeImage)this.data).writeToFile(file);
   }

   @MappedMethod
   public int getHeight() {
      return ((com.mojang.blaze3d.platform.NativeImage)this.data).getHeight();
   }

   @MappedMethod
   public void fillRect(int i, int j, int k, int l, int m) {
      ((com.mojang.blaze3d.platform.NativeImage)this.data).fillRect(i, j, k, l, m);
   }

   @MappedMethod
   public int getWidth() {
      return ((com.mojang.blaze3d.platform.NativeImage)this.data).getWidth();
   }

   @MappedMethod
   public void setPixel(int i, int j, int k) {
      ((com.mojang.blaze3d.platform.NativeImage)this.data).setPixel(i, j, k);
   }

   @MappedMethod
   public void setPixelABGR(int i, int j, int k) {
      ((com.mojang.blaze3d.platform.NativeImage)this.data).setPixelABGR(i, j, k);
   }

   @MappedMethod
   public int getPixel(int i, int j) {
      return ((com.mojang.blaze3d.platform.NativeImage)this.data).getPixel(i, j);
   }

   @Nonnull
   @MappedMethod
   public static NativeImage read(byte[] bs) throws IOException {
      return new NativeImage(com.mojang.blaze3d.platform.NativeImage.read(bs));
   }

   @Nonnull
   @MappedMethod
   public static NativeImage read(NativeImageFormat format, ByteBuffer byteBuffer) throws IOException {
      return convertFormat(com.mojang.blaze3d.platform.NativeImage.read(byteBuffer), format);
   }

   @Nonnull
   @MappedMethod
   public static NativeImage read(NativeImageFormat format, InputStream inputStream) throws IOException {
      return convertFormat(com.mojang.blaze3d.platform.NativeImage.read(inputStream), format);
   }

   @Nonnull
   @MappedMethod
   public static NativeImage read(InputStream inputStream) throws IOException {
      return new NativeImage(com.mojang.blaze3d.platform.NativeImage.read(inputStream));
   }

   @Nonnull
   @MappedMethod
   public static NativeImage read(ByteBuffer byteBuffer) throws IOException {
      return new NativeImage(com.mojang.blaze3d.platform.NativeImage.read(byteBuffer));
   }

   @MappedMethod
   public int getColor(int x, int y) {
      return this.getPixel(x, y);
   }

   @MappedMethod
   public void setPixelColor(int x, int y, int color) {
      this.setPixel(x, y, color);
   }



@MappedMethod
   public NativeImage(NativeImageFormat format, int i, int j, boolean bl) {
      super(new com.mojang.blaze3d.platform.NativeImage(format.data, i, j, bl));
   }

@MappedMethod
   public NativeImage(int i, int j, boolean bl) {
      super(new com.mojang.blaze3d.platform.NativeImage(i, j, bl));
   }
    private static NativeImage convertFormat(com.mojang.blaze3d.platform.NativeImage image, NativeImageFormat format) {
      if(image.format() == format.data) return new NativeImage(image);
      try(image) {
         var result = new com.mojang.blaze3d.platform.NativeImage(format.data, image.getWidth(), image.getHeight(), false);
         try {
            var pixels=result.getPixelBytes();
            for(int y=0;y<image.getHeight();y++)for(int x=0;x<image.getWidth();x++) {
               int c=image.getPixel(x,y), r=(c>>>16)&255, g=(c>>>8)&255, b=c&255;
               int offset=(y*image.getWidth()+x)*format.data.components();
               if(format.data.hasLuminance()) pixels.put(offset,(byte)((r*77+g*150+b*29)>>>8));
               else {pixels.put(offset,(byte)r);pixels.put(offset+1,(byte)g);pixels.put(offset+2,(byte)b);}
               if(format.data.hasAlpha()) pixels.put(offset+format.data.alphaOffset()/8,(byte)(c>>>24));
            }
            return new NativeImage(result);
         }catch(RuntimeException|Error failure){result.close();throw failure;}
      }
   }
}
