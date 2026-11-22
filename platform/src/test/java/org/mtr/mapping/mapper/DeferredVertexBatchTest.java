package org.mtr.mapping.mapper;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeferredVertexBatchTest {
    @Test void submittedFrameCannotBeChangedByNextFrame() {
        List<SubmitNodeCollector.CustomGeometryRenderer> draws=new ArrayList<>();
        SubmitNodeCollector collector=(SubmitNodeCollector)Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{SubmitNodeCollector.class},(p,m,a)->{
            if(m.getName().equals("submitCustomGeometry"))draws.add((SubmitNodeCollector.CustomGeometryRenderer)a[2]);
            return null;
        });
        DeferredVertexBatch batch=new DeferredVertexBatch();
        batch.addVertex(1,2,3).setColor(0x80402010).setUv(.25F,.75F).setUv1(3,4).setUv2(5,6).setUv3(.1F,.2F).setNormal(0,1,0).setLineWidth(2);
        batch.submit(collector,null);
        batch.addVertex(9,8,7).setColor(0xFFFFFFFF);
        batch.submit(collector,null);
        assertEquals(2,draws.size());
        List<String> calls=new ArrayList<>();
        VertexConsumer target=(VertexConsumer)Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{VertexConsumer.class},(p,m,a)->{
            calls.add(m.getName()+java.util.Arrays.toString(a));return p;
        });
        draws.getFirst().render(new PoseStack().last(),target);
        assertTrue(calls.contains("addVertex[1.0, 2.0, 3.0]"));
        assertTrue(calls.contains("setColor[-2143281136]"));
        assertTrue(calls.contains("setUv[0.25, 0.75]"));
        assertTrue(calls.contains("setUv1[3, 4]"));assertTrue(calls.contains("setUv2[5, 6]"));
        assertTrue(calls.contains("setLineWidth[2.0]"));
        assertEquals(1,calls.stream().filter(c->c.startsWith("addVertex")).count());
        assertFalse(calls.contains("addVertex[9.0, 8.0, 7.0]"));
    }
}
