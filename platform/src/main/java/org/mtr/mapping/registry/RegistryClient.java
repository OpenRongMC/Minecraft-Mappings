package org.mtr.mapping.registry;

import com.mojang.blaze3d.platform.InputConstants.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import org.mtr.mapping.mapper.LegacyBlockLayers;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import io.netty.buffer.Unpooled;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping.Category;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockColorProvider;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockRenderView;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.ClientWorld;
import org.mtr.mapping.holder.ItemColorProvider;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.KeyBinding;
import org.mtr.mapping.holder.LivingEntity;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.SpriteProvider;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.EntityExtension;
import org.mtr.mapping.mapper.EntityRenderer;
import org.mtr.mapping.mapper.LegacyModelPredicate;
import org.mtr.mapping.mapper.ParticleFactoryExtension;
import org.mtr.mapping.tool.DummyClass;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class RegistryClient extends DummyClass {
   public static Function<World, ? extends EntityExtension> worldRenderingEntity;
   public final EventRegistryClient eventRegistryClient = new EventRegistryClient();
   private final Registry registry;
   private final List<Runnable> objectsToRegister = new ArrayList<>();
   private final Map<String, Category> keyBindingCategories = new HashMap<>();

   @MappedMethod
   public RegistryClient(Registry registry) {
      this.registry = registry;
   }

   @MappedMethod
   public void init() {
      this.objectsToRegister.forEach(Runnable::run);
   }

   @MappedMethod
   public <T extends BlockEntityTypeRegistryObject<U>, U extends BlockEntityExtension> void registerBlockEntityRenderer(
      T blockEntityType, Function<BlockEntityRenderer.Argument, BlockEntityRenderer<U>> rendererInstance
   ) {
      this.objectsToRegister
         .add(
            () -> BlockEntityRenderers.register(
               (BlockEntityType)blockEntityType.get().data, context -> rendererInstance.apply(new BlockEntityRenderer.Argument(context))
            )
         );
   }

   @MappedMethod
   public <T extends EntityTypeRegistryObject<U>, U extends EntityExtension> void registerEntityRenderer(
      T entityType, Function<EntityRenderer.Argument, EntityRenderer<U>> rendererInstance
   ) {
      this.objectsToRegister
         .add(
            () -> EntityRendererRegistry.register(
               (EntityType)entityType.get().data, dispatcher -> rendererInstance.apply(new EntityRenderer.Argument(dispatcher))
            )
         );
   }

   @MappedMethod
   public void registerParticleRenderer(ParticleTypeRegistryObject particleTypeRegistryObject, Function<SpriteProvider, ParticleFactoryExtension> factory) {
      ParticleProviderRegistry.getInstance()
         .register((ParticleType)particleTypeRegistryObject.get().data, spriteProvider -> factory.apply(new SpriteProvider(spriteProvider)));
   }

   @MappedMethod
   public void registerBlockRenderType(RenderLayer renderLayer, BlockRegistryObject block) {
      this.objectsToRegister.add(() -> {
         ChunkSectionLayer layer;
         if (renderLayer.data == RenderTypes.translucentMovingBlock()) {
            layer = ChunkSectionLayer.TRANSLUCENT;
         } else if (renderLayer.data != RenderTypes.cutoutMovingBlock()) {
            String name = String.valueOf(renderLayer.data).toLowerCase();
            if (name.contains("translucent")) {
               layer = ChunkSectionLayer.TRANSLUCENT;
            } else if (!name.contains("cutout") && !name.contains("tripwire")) {
               layer = ChunkSectionLayer.SOLID;
            } else {
               layer = ChunkSectionLayer.CUTOUT;
            }
         } else {
            layer = ChunkSectionLayer.CUTOUT;
         }

         LegacyBlockLayers.register((Block)block.get().data, layer);
      });
   }

   @MappedMethod
   public KeyBinding registerKeyBinding(String translationKey, int key, String categoryKey) {
      String categoryId = categoryKey.contains(":") ? categoryKey : "mtr:" + categoryKey;
      Category category = this.keyBindingCategories.computeIfAbsent(categoryId, id -> {
         Identifier identifier = Identifier.parse(id);

         try {
            return Category.register(identifier);
         } catch (IllegalArgumentException var3x) {
            return new Category(identifier);
         }
      });
      return new KeyBinding(KeyMappingHelper.registerKeyMapping(new KeyMapping(translationKey, Type.KEYBOARD, org.mtr.mapping.mapper.LegacyInput.keyToNative(key), category)));
   }

   @MappedMethod
   public void registerBlockColors(BlockColorProvider blockColorProvider, BlockRegistryObject... blocks) {
      Block[] newBlocks = new Block[blocks.length];

      for (int i = 0; i < blocks.length; i++) {
         newBlocks[i] = (Block)blocks[i].get().data;
      }

      BlockColorRegistry.register((blockState, world, pos, tints) -> {
         // All bundled MTR/addon block models use tint index 0.
         tints.add(blockColorProvider.getColor2(new BlockState(blockState),
            world == null ? null : new BlockRenderView(world), pos == null ? null : new BlockPos(pos), 0) | 0xFF000000);
      }, newBlocks);
   }

   @MappedMethod
   public void registerItemColors(ItemColorProvider itemColorProvider, ItemRegistryObject... items) {
      for (ItemRegistryObject item : items) org.mtr.mapping.mapper.LegacyItemTints.register((Item)item.get().data, itemColorProvider);
   }

   @MappedMethod
   public void registerItemModelPredicate(
      ItemRegistryObject item, org.mtr.mapping.holder.Identifier identifier, RegistryClient.ModelPredicateProvider modelPredicateProvider
   ) {
      this.objectsToRegister.add(() -> LegacyModelPredicate.register((Item)item.get().data, (Identifier)identifier.data, modelPredicateProvider));
   }

   @MappedMethod
   public void setupPackets(org.mtr.mapping.holder.Identifier identifier) {
      this.registry.packetsIdentifier = identifier;
      ClientPlayNetworking.registerGlobalReceiver(
         RawBytePayload.ensureRegistered((Identifier)identifier.data),
         (payload, context) -> PacketBufferReceiver.receive(payload.toByteBuf(), packetBufferReceiver -> {
            Function<PacketBufferReceiver, ? extends PacketHandler> getInstance = this.registry.packets.get(packetBufferReceiver.readString());
            if (getInstance != null) {
               getInstance.apply(packetBufferReceiver).runClient();
            }
         }, context.client()::execute)
      );
   }

   @MappedMethod
   public <T extends PacketHandler> void sendPacketToServer(T data) {
      if (this.registry.packetsIdentifier != null) {
         PacketBufferSender packetBufferSender = new PacketBufferSender(Unpooled::buffer);
         packetBufferSender.writeString(data.getClass().getName());
         data.write(packetBufferSender);
         packetBufferSender.send(
            byteBuf -> ClientPlayNetworking.send(new RawBytePayload((Identifier)this.registry.packetsIdentifier.data, RawBytePayload.fromByteBuf(byteBuf))),
            runnable -> Minecraft.getInstance().execute(runnable)
         );
      }
   }

   @FunctionalInterface
   public interface ModelPredicateProvider {
      @MappedMethod
      float call(ItemStack var1, @Nullable ClientWorld var2, @Nullable LivingEntity var3);
   }
}
