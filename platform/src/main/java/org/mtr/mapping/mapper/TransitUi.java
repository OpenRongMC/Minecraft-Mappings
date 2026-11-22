package org.mtr.mapping.mapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

/** Shared surfaces and controls for the MTR and YTE screens. */
public final class TransitUi {
    public static final int SURFACE = 0xF5182638;
    public static final int INSET = 0xEE101C2C;
    public static final int BORDER = 0xFF354962;
    public static final int TEXT = 0xFFF0F6FF;
    public static final int MUTED_TEXT = 0xFFB4C6DA;
    public static final int MTR_ACCENT = 0xFF59C6FE;
    public static final int YTE_ACCENT = 0xFFC7ABFF;

    private static Extension extension;

    public interface Extension {
        boolean background(Screen screen, GuiGraphicsExtractor graphics, int width, int height);
        boolean control(Screen screen, GuiGraphicsExtractor graphics, int x, int y, int width, int height,
                boolean enabled, boolean highlighted);
        void close(Screen screen);
    }

    public static void install(Extension appearance) {
        extension = java.util.Objects.requireNonNull(appearance);
    }

    static boolean backdrop(Screen screen, GuiGraphicsExtractor graphics, int width, int height) {
        return extension != null && extension.background(screen, graphics, width, height);
    }

    public static boolean customControl(GraphicsHolder graphics, AbstractWidget widget, int width, int height) {
        return graphics.drawContext != null && customControl(graphics.drawContext, widget, width, height);
    }

    static boolean customControl(GuiGraphicsExtractor graphics, AbstractWidget widget, int width, int height) {
        return extension != null && extension.control(
                Minecraft.getInstance().gui.screen(), graphics, widget.getX(), widget.getY(), width, height,
                widget.active, widget.isHoveredOrFocused());
    }

    static void close(Screen screen) {
        if (extension != null) extension.close(screen);
    }

    private TransitUi() { }

    static TransitUi.Palette current() {
        return palette(Minecraft.getInstance().gui.screen());
    }

    static Palette palette(Screen screen) {
        if (screen == null) return null;
        for (Class<?> type = screen.getClass(); type != null; type = type.getSuperclass()) {
            final String name = type.getPackageName();
            if (name.startsWith("org.mtr.mod.screen")) return Palette.MTR;
            if (name.startsWith("top.xfunny.mod.client.screen")) return Palette.YTE;
        }
        return null;
    }

    static void background(GuiGraphicsExtractor graphics, int width, int height, Palette palette) {
        graphics.fillGradient(0, 0, width, height, 0xF5101E30, 0xE50C1420);
        graphics.fill(0, 0, width, 2, palette.accent);
    }

    public static void rectangle(GraphicsHolder graphics, int x, int y, int width, int height, int color) {
        if (graphics.drawContext != null && width > 0 && height > 0) {
            graphics.drawContext.fill(x, y, x + width, y + height, color);
        }
    }

    public static void panel(GraphicsHolder graphics, int x, int y, int width, int height) {
        rectangle(graphics, x, y, width, height, BORDER);
        rectangle(graphics, x + 1, y + 1, width - 2, height - 2, SURFACE);
    }

    public static void clip(GraphicsHolder graphics, int x, int y, int width, int height, Runnable draw) {
        if (graphics.drawContext == null || width <= 0 || height <= 0) return;
        graphics.drawContext.enableScissor(x, y, x + width, y + height);
        try {
            draw.run();
        } finally {
            graphics.drawContext.disableScissor();
        }
    }

    static void control(GuiGraphicsExtractor graphics, AbstractWidget widget, Palette palette) {
        final int x = widget.getX();
        final int y = widget.getY();
        final int width = widget.getWidth();
        final int height = widget.getHeight();
        if (width < 4 || height < 4) return;
        final boolean highlighted = widget.active && widget.isHoveredOrFocused();
        final int border = highlighted ? palette.accent : BORDER;
        final int fill = !widget.active ? 0xFF1C293A : highlighted ? 0xFF294661 : 0xFF20334C;
        graphics.fill(x + 1, y + 2, x + width - 1, y + height - 1, border);
        graphics.fill(x + 2, y + 3, x + width - 2, y + height - 2, fill);
    }

    enum Palette {
        MTR(MTR_ACCENT), YTE(YTE_ACCENT);
        final int accent;

        Palette(int accent) {
            this.accent = accent;
        }
    }
}
