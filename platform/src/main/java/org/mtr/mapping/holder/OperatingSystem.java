package org.mtr.mapping.holder;

import java.io.File;
import java.net.URI;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.Util.OS;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum OperatingSystem {
   LINUX(OS.LINUX),
   SOLARIS(OS.SOLARIS),
   WINDOWS(OS.WINDOWS),
   OSX(OS.OSX),
   UNKNOWN(OS.UNKNOWN);

   public final OS data;

   private OperatingSystem(OS data) {
      this.data = data;
   }

   public static OperatingSystem convert(@Nullable OS data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @MappedMethod
   public void openUri(URI uRI) {
      if (!org.lwjgl.sdl.SDLMisc.SDL_OpenURL(uRI.toASCIIString())) throw new IllegalStateException("SDL could not open URI");
   }

   @MappedMethod
   public void openUri(String string) {
      this.openUri(URI.create(string));
   }

   @MappedMethod
   public void openFile(File file) {
      this.openUri(file.toURI());
   }

   @MappedMethod
   public void open(String uri) {
      this.openUri(uri);
   }

   @MappedMethod
   public void open(File file) {
      this.openFile(file);
   }
}
