package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class ScreenAbstractMapping extends net.minecraft.client.gui.screens.Screen {

   @MappedMethod
   public final void init2(int i, int j) {
      super.init(i, j);
   }

   @MappedMethod
   public boolean mouseScrolled2(double d, double e, double f, double g) {
      return super.mouseScrolled(d, e, f, g);
   }

   @Deprecated
   public final boolean mouseScrolled(double d, double e, double f, double g) {
      return this.mouseScrolled2(d, e, f, g);
   }

   @MappedMethod
   protected void init2() {
      super.init();
   }

   @Deprecated
   protected final void init() {
      this.init2();
   }

   @MappedMethod
   public void renderBackground2(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      super.extractBackground(guiGraphics, i, j, f);
   }

   @MappedMethod
   public void onClose2() {
      super.onClose();
   }

   @Deprecated
   public final void onClose() {
      this.onClose2();
   }

   @MappedMethod
   public boolean isPauseScreen2() {
      return super.isPauseScreen();
   }

   @Deprecated
   public final boolean isPauseScreen() {
      return this.isPauseScreen2();
   }

   @Nonnull
   @MappedMethod
   protected <T extends GuiEventListener & NarratableEntry> T addWidget2(T guiEventListener) {
      return (T)super.addWidget(guiEventListener);
   }

   @Deprecated
   protected final <T extends GuiEventListener & NarratableEntry> T addWidget(T guiEventListener) {
      return this.addWidget2(guiEventListener);
   }

   @MappedMethod
   public void removed2() {
      super.removed();
   }

   @Deprecated
   public final void removed() {
      this.removed2();
   }

   @MappedMethod
   public void mouseMoved2(double d, double e) {
      super.mouseMoved(d, e);
   }

   @Deprecated
   public final void mouseMoved(double d, double e) {
      this.mouseMoved2(d, e);
   }

   @MappedMethod
   public boolean mouseClicked2(MouseButtonEvent mouseButtonEvent, boolean bl) {
      return super.mouseClicked(mouseButtonEvent, bl);
   }

   @Deprecated
   public final boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean bl) {
      return this.mouseClicked2(mouseButtonEvent, bl);
   }

   @MappedMethod
   public void tick2() {
      super.tick();
   }

   @Deprecated
   public final void tick() {
      this.tick2();
   }

   @MappedMethod
   public boolean mouseReleased2(MouseButtonEvent mouseButtonEvent) {
      return super.mouseReleased(mouseButtonEvent);
   }

   @Deprecated
   public final boolean mouseReleased(MouseButtonEvent mouseButtonEvent) {
      return this.mouseReleased2(mouseButtonEvent);
   }

   @MappedMethod
   public boolean mouseDragged2(MouseButtonEvent mouseButtonEvent, double d, double e) {
      return super.mouseDragged(mouseButtonEvent, d, e);
   }

   @Deprecated
   public final boolean mouseDragged(MouseButtonEvent mouseButtonEvent, double d, double e) {
      return this.mouseDragged2(mouseButtonEvent, d, e);
   }

   @MappedMethod
   public void resize2(int i, int j) {
      super.resize(i, j);
   }

   @Deprecated
   public final void resize(int i, int j) {
      this.resize2(i, j);
   }

   @Nonnull
   @MappedMethod
   protected <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget2(T guiEventListener) {
      return (T)super.addRenderableWidget(guiEventListener);
   }

   @Deprecated
   protected final <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T guiEventListener) {
      return this.addRenderableWidget2(guiEventListener);
   }

   @MappedMethod
   public void render2(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      super.extractRenderState(guiGraphics, i, j, f);
   }

   @MappedMethod
   public int getWidthMapped() {
      return this.width;
   }

   @MappedMethod
   public int getHeightMapped() {
      return this.height;
   }

@MappedMethod
   public ScreenAbstractMapping(Text component) {
      super((Component)component.data);
   }



@Deprecated
   public final void extractBackground(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      this.renderBackground2(guiGraphics, i, j, f);
   }

@Deprecated
   public final void extractRenderState(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      this.render2(guiGraphics, i, j, f);
   }
}
