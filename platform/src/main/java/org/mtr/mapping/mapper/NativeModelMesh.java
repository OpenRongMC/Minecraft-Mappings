package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.model.Face;
import org.mtr.mapping.render.model.RawMesh;

/** Immutable reusable model geometry; Minecraft owns GPU uploads and render passes. */
record NativeModelMesh(MaterialProperties material, List<Point> vertices) {
    record Point(float x,float y,float z,float u,float v,float nx,float ny,float nz){}
    static NativeModelMesh compile(RawMesh mesh) {
        mesh.validateVertexIndex();
        List<Point> points=new ArrayList<>();
        for(Face face:mesh.faces) {
            // Native entity layers use quads. A repeated final vertex represents each triangle exactly.
            for(Face triangle:Face.triangulate(face.vertices)) {
                for(int corner:new int[]{0,1,2,2}) {
                    var vertex=mesh.vertices.get(triangle.vertices[corner]);
                    points.add(new Point(vertex.position.getX(),vertex.position.getY(),vertex.position.getZ(),vertex.u,vertex.v,
                        vertex.normal.getX(),vertex.normal.getY(),vertex.normal.getZ()));
                }
            }
        }
        return new NativeModelMesh(mesh.materialProperties,List.copyOf(points));
    }
    void submit(GraphicsHolder graphics,int color,int light) {
        if(graphics.vertexConsumerProvider==null || graphics.matrixStack==null || vertices.isEmpty())return;
        int effectiveColor=material.vertexAttributeState.color==null?color:multiplyColor(color,material.vertexAttributeState.color);
        int effectiveLight=material.vertexAttributeState.lightmapUV==null?light:material.vertexAttributeState.lightmapUV;
        var layer=(net.minecraft.client.renderer.rendertype.RenderType)material.getBlazeRenderType().data;
        graphics.vertexConsumerProvider.submitCustomGeometry(graphics.matrixStack,layer,(pose,out)-> {
            for(Point v:vertices)out.addVertex(pose,v.x,v.y,v.z).setColor(effectiveColor).setUv(v.u,v.v)
                .setOverlay(OverlayTexture.NO_OVERLAY).setLight(effectiveLight).setNormal(pose,v.nx,v.ny,v.nz);
        });
    }
    private static int multiplyColor(int a,int b) {
        int result=0;for(int shift:new int[]{0,8,16,24})result|=(((a>>>shift)&255)*((b>>>shift)&255)/255)<<shift;return result;
    }
}
