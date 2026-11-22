package org.mtr.mapping.mapper;

import java.util.function.Supplier;
import org.mtr.mapping.render.shader.ModShaderHandler;

/** Submits reusable model snapshots to Minecraft's backend-independent render pipeline. */
public final class OptimizedRenderer {
    private boolean renderTranslucent = true;
    public void setRenderTranslucent(boolean enabled) { renderTranslucent = enabled; }
    public void beginReload() { }
    public void finishReload() { }
    // Compatibility entry points. Native submissions do not alter global OpenGL state.
    public void runWithProtectedState(Runnable task) { task.run(); }
    public <T> T runWithProtectedState(Supplier<T> task) { return task.get(); }
    public void queue(OptimizedModel model,GraphicsHolder graphics,int color,int light) {
        for(NativeModelMesh part:model.uploadedParts) if(renderTranslucent || !part.material().translucent) part.submit(graphics,color,light);
    }
    /** Pass scheduling belongs to the native collector, including translucent depth sorting. */
    public void render(boolean translucent) { renderTranslucent = translucent; }
    public static boolean renderingShadows() { return ModShaderHandler.renderingShadows(); }
    public static boolean hasOptimizedRendering() { return true; }
}
