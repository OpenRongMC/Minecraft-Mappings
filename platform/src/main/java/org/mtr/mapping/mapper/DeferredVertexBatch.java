package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;

/** CPU extraction buffer. Only immutable vertex values cross into the drawing phase. */
final class DeferredVertexBatch implements VertexConsumer {
    private final List<Vertex> vertices = new ArrayList<>();
    private Vertex current;
    private static final class Vertex {
        float x, y, z, u, v, u3, v3, nx, ny, nz, width = 1;
        int color = -1, overlayU, overlayV, lightU, lightV;
    }
    @Override public VertexConsumer addVertex(float x, float y, float z) {
        current = new Vertex(); current.x=x; current.y=y; current.z=z;
        vertices.add(current); return this;
    }
    @Override public VertexConsumer setColor(int r,int g,int b,int a) {return setColor((a<<24)|(r<<16)|(g<<8)|b);}
    @Override public VertexConsumer setColor(int color) {current.color=color;return this;}
    @Override public VertexConsumer setUv(float u,float v) {current.u=u;current.v=v;return this;}
    @Override public VertexConsumer setUv1(int u,int v) {current.overlayU=u;current.overlayV=v;return this;}
    @Override public VertexConsumer setUv2(int u,int v) {current.lightU=u;current.lightV=v;return this;}
    @Override public VertexConsumer setUv3(float u,float v) {current.u3=u;current.v3=v;return this;}
    @Override public VertexConsumer setNormal(float x,float y,float z) {current.nx=x;current.ny=y;current.nz=z;return this;}
    @Override public VertexConsumer setLineWidth(float width) {current.width=width;return this;}
    void submit(SubmitNodeCollector collector, RenderType layer) {
        if(vertices.isEmpty()) return;
        // Seal this batch before the renderer can consume it. No entity, world or mutable pose is retained.
        Vertex[] snapshot=vertices.toArray(Vertex[]::new);
        vertices.clear(); current=null;
        collector.submitCustomGeometry(new PoseStack(),layer,(pose,out)->{
            for(Vertex v:snapshot) out.addVertex(v.x,v.y,v.z).setColor(v.color)
                .setUv(v.u,v.v).setUv1(v.overlayU,v.overlayV).setUv2(v.lightU,v.lightV)
                .setUv3(v.u3,v.v3).setNormal(v.nx,v.ny,v.nz).setLineWidth(v.width);
        });
    }
}
