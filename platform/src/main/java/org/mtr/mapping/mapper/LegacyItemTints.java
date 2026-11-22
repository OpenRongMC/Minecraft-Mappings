package org.mtr.mapping.mapper;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.CompositeModel;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mtr.mapping.holder.ItemColorProvider;

/** Converts legacy item colour callbacks into native, reloadable model tint sources. */
public final class LegacyItemTints {
    private static final Map<Identifier,ItemColorProvider> PROVIDERS=new ConcurrentHashMap<>();
    private static boolean installed;
    public static void register(Item item,ItemColorProvider provider) {
        PROVIDERS.put(BuiltInRegistries.ITEM.getKey(item),provider);
        if(installed)return;
        installed=true;
        ModelLoadingPlugin.register(context->context.modifyItemModelBeforeBake().register((model,bake)-> {
            var callback=PROVIDERS.get(bake.itemId());
            return callback==null?model:adapt(model,callback);
        }));
    }
    static ItemModel.Unbaked adapt(ItemModel.Unbaked model,ItemColorProvider provider) {
        if(model instanceof CuboidItemModelWrapper.Unbaked cuboid) {
            int count=Math.max(1,cuboid.tints().size());
            var tints=new java.util.ArrayList<ItemTintSource>(count);
            for(int index=0;index<count;index++)tints.add(new CallbackTint(provider,index));
            return new CuboidItemModelWrapper.Unbaked(cuboid.model(),cuboid.transformation(),List.copyOf(tints));
        }
        if(model instanceof CompositeModel.Unbaked composite)
            return new CompositeModel.Unbaked(composite.models().stream().map(child->adapt(child,provider)).toList(),composite.transformation());
        if(model instanceof ConditionalItemModel.Unbaked conditional)
            return new ConditionalItemModel.Unbaked(conditional.transformation(),conditional.property(),adapt(conditional.onTrue(),provider),adapt(conditional.onFalse(),provider));
        return model;
    }
    private record CallbackTint(ItemColorProvider provider,int index) implements ItemTintSource {
        @Override public int calculate(ItemStack stack,ClientLevel level,LivingEntity entity) {
            return provider.getColor2(new org.mtr.mapping.holder.ItemStack(stack),index)|0xFF000000;
        }
        @Override public MapCodec<? extends ItemTintSource> type(){return MapCodec.unit(this);}
    }
    private LegacyItemTints(){}
}
