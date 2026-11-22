package org.mtr.mapping.mapper;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.client.renderer.v1.model.FabricBlockStateModel;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** Adapts registered 1.21 block layers to 26.3's per-quad materials. */
public final class LegacyBlockLayers {
    private static final Map<Block,ChunkSectionLayer> LAYERS=new IdentityHashMap<>();
    private static boolean installed;
    public static void register(Block block,ChunkSectionLayer layer) {
        LAYERS.put(block,layer);
        if(installed)return;
        installed=true;
        ModelLoadingPlugin.register(context->context.modifyBlockModelAfterBake().register((model,bake)-> {
            ChunkSectionLayer mapped=LAYERS.get(bake.state().getBlock());
            return mapped==null?model:new LayerModel(model,mapped);
        }));
    }
    private record LayerModel(BlockStateModel original,ChunkSectionLayer layer) implements BlockStateModel,FabricBlockStateModel {
        @Override public void collectParts(RandomSource random,List<BlockStateModelPart> parts){original.collectParts(random,parts);}
        @Override public Material.Baked particleMaterial(){return original.particleMaterial();}
        @Override public int materialFlags(){return original.materialFlags()|(layer==ChunkSectionLayer.TRANSLUCENT?BakedQuad.FLAG_TRANSLUCENT:0);}
        @Override public Object createGeometryKey(BlockAndTintGetter world,BlockPos pos,BlockState state,RandomSource random){return ((FabricBlockStateModel)original).createGeometryKey(world,pos,state,random);}
        @Override public void emitQuads(QuadEmitter emitter,BlockAndTintGetter world,BlockPos pos,BlockState state,RandomSource random,Predicate<Direction> cullTest){
            emitter.pushTransform(quad->{quad.chunkLayer(layer);return true;});
            try{((FabricBlockStateModel)original).emitQuads(emitter,world,pos,state,random,cullTest);}
            finally{emitter.popTransform();}
        }
    }
    private LegacyBlockLayers(){}
}
