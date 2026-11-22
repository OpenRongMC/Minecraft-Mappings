package org.mtr.mapping.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents.ModifyOutput;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.DefaultParticleType;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.ItemSettings;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockItemExtension;
import org.mtr.mapping.mapper.EntityExtension;
import org.mtr.mapping.mapper.SlabBlockExtension;
import org.mtr.mapping.tool.DummyClass;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class Registry extends DummyClass {
   Identifier packetsIdentifier;
   final Map<String, Function<PacketBufferReceiver, ? extends PacketHandler>> packets = new HashMap<>();
   public final EventRegistry eventRegistry = new EventRegistry();
   private final List<Runnable> objectsToRegister = new ArrayList<>();
   private final List<Consumer<CommandDispatcher<CommandSourceStack>>> commandsToRegister = new ArrayList<>();
   public static final ThreadLocal<Identifier> PENDING_BLOCK_ID = new ThreadLocal<>();
   public static final ThreadLocal<Identifier> PENDING_ITEM_ID = new ThreadLocal<>();
   public static final ThreadLocal<Boolean> PENDING_ITEM_USE_BLOCK_DESC = new ThreadLocal<>();
   public static final ThreadLocal<String> PENDING_ITEM_DESCRIPTION = new ThreadLocal<>();

   public static String pendingBlockDescription() {
      Identifier id = PENDING_BLOCK_ID.get();
      return id == null ? null : descriptionId("block.", id);
   }

   public static String pendingItemDescription() {
      String explicit = PENDING_ITEM_DESCRIPTION.get();
      if (explicit != null) {
         return sanitizeDescription(explicit);
      } else {
         Identifier id = PENDING_ITEM_ID.get();
         if (id == null) {
            return null;
         } else {
            String prefix = Boolean.TRUE.equals(PENDING_ITEM_USE_BLOCK_DESC.get()) ? "block." : "item.";
            return descriptionId(prefix, id);
         }
      }
   }

   private static String descriptionId(String prefix, Identifier id) {
      return sanitizeDescription(
         prefix + ((net.minecraft.resources.Identifier)id.data).getNamespace() + "." + ((net.minecraft.resources.Identifier)id.data).getPath()
      );
   }

   private static String sanitizeDescription(String key) {
      return key.replace('/', '.');
   }

   private static String translationKeyOf(Block block) {
      String key;
      if (block.data instanceof BlockExtension) {
         key = ((BlockExtension)block.data).getTranslationKey2();
      } else if (block.data instanceof SlabBlockExtension) {
         key = ((SlabBlockExtension)block.data).getTranslationKey2();
      } else {
         key = ((net.minecraft.world.level.block.Block)block.data).getDescriptionId();
      }

      return sanitizeDescription(key);
   }

   @MappedMethod
   public void init() {
      this.objectsToRegister.forEach(Runnable::run);
      CommandRegistrationCallback.EVENT
         .register(
            (CommandRegistrationCallback)(dispatcher, commandRegistryAccess, environment) -> this.commandsToRegister
               .forEach(consumer -> consumer.accept(dispatcher))
         );
   }

   private static <T> void restorePending(ThreadLocal<T> local, T previous) {
      if (previous == null) {
         local.remove();
      } else {
         local.set(previous);
      }
   }

   @MappedMethod
   public BlockRegistryObject registerBlock(Identifier identifier, Supplier<Block> supplier) {
      Identifier previousBlockId = PENDING_BLOCK_ID.get();
      PENDING_BLOCK_ID.set(identifier);

      Block block;
      try {
         block = supplier.get();
      } finally {
         restorePending(PENDING_BLOCK_ID, previousBlockId);
      }

      this.objectsToRegister
         .add(
            () -> net.minecraft.core.Registry.register(
               BuiltInRegistries.BLOCK, (net.minecraft.resources.Identifier)identifier.data, (net.minecraft.world.level.block.Block)block.data
            )
         );
      return new BlockRegistryObject(block);
   }

   @MappedMethod
   public BlockRegistryObject registerBlockWithBlockItem(Identifier identifier, Supplier<Block> supplier, CreativeModeTabHolder... creativeModeTabHolders) {
      return this.registerBlockWithBlockItem(identifier, supplier, BlockItemExtension::new, creativeModeTabHolders);
   }

   @MappedMethod
   public BlockRegistryObject registerBlockWithBlockItem(
      Identifier identifier,
      Supplier<Block> supplier,
      BiFunction<Block, ItemSettings, BlockItemExtension> function,
      CreativeModeTabHolder... creativeModeTabHolders
   ) {
      Identifier previousBlockId = PENDING_BLOCK_ID.get();
      PENDING_BLOCK_ID.set(identifier);

      Block block;
      try {
         block = supplier.get();
      } finally {
         restorePending(PENDING_BLOCK_ID, previousBlockId);
      }

      this.objectsToRegister
         .add(
            () -> net.minecraft.core.Registry.register(
               BuiltInRegistries.BLOCK, (net.minecraft.resources.Identifier)identifier.data, (net.minecraft.world.level.block.Block)block.data
            )
         );
      Identifier previousItemId = PENDING_ITEM_ID.get();
      Boolean previousUseBlockDesc = PENDING_ITEM_USE_BLOCK_DESC.get();
      String previousItemDescription = PENDING_ITEM_DESCRIPTION.get();
      PENDING_ITEM_ID.set(identifier);
      PENDING_ITEM_USE_BLOCK_DESC.set(true);
      PENDING_ITEM_DESCRIPTION.set(translationKeyOf(block));

      BlockItemExtension blockItemExtension;
      try {
         blockItemExtension = function.apply(block, new ItemSettings().useBlockDescriptionPrefix());
      } finally {
         restorePending(PENDING_ITEM_ID, previousItemId);
         restorePending(PENDING_ITEM_USE_BLOCK_DESC, previousUseBlockDesc);
         restorePending(PENDING_ITEM_DESCRIPTION, previousItemDescription);
      }

      this.objectsToRegister
         .add(() -> net.minecraft.core.Registry.register(BuiltInRegistries.ITEM, (net.minecraft.resources.Identifier)identifier.data, blockItemExtension));

      for (CreativeModeTabHolder creativeModeTabHolder : creativeModeTabHolders) {
         CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, creativeModeTabHolder.identifier))
            .register((ModifyOutput)content -> content.accept(blockItemExtension));
      }

      return new BlockRegistryObject(block);
   }

   @MappedMethod
   public ItemRegistryObject registerItem(Identifier identifier, Function<ItemSettings, Item> function, CreativeModeTabHolder... creativeModeTabHolders) {
      Identifier previousItemId = PENDING_ITEM_ID.get();
      Boolean previousUseBlockDesc = PENDING_ITEM_USE_BLOCK_DESC.get();
      String previousItemDescription = PENDING_ITEM_DESCRIPTION.get();
      PENDING_ITEM_ID.set(identifier);
      PENDING_ITEM_USE_BLOCK_DESC.remove();
      PENDING_ITEM_DESCRIPTION.remove();

      Item item;
      try {
         item = function.apply(new ItemSettings());
      } finally {
         restorePending(PENDING_ITEM_ID, previousItemId);
         restorePending(PENDING_ITEM_USE_BLOCK_DESC, previousUseBlockDesc);
         restorePending(PENDING_ITEM_DESCRIPTION, previousItemDescription);
      }

      this.objectsToRegister
         .add(
            () -> net.minecraft.core.Registry.register(
               BuiltInRegistries.ITEM, (net.minecraft.resources.Identifier)identifier.data, (net.minecraft.world.item.Item)item.data
            )
         );

      for (CreativeModeTabHolder creativeModeTabHolder : creativeModeTabHolders) {
         CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, creativeModeTabHolder.identifier))
            .register((ModifyOutput)content -> content.accept((ItemLike)item.data));
      }

      return new ItemRegistryObject(item);
   }

   @MappedMethod
   public <T extends BlockEntityExtension> BlockEntityTypeRegistryObject<T> registerBlockEntityType(
      Identifier identifier, BiFunction<BlockPos, BlockState, T> function, Supplier<Block>... blockSuppliers
   ) {
      BlockEntityType<T> blockEntityType = FabricBlockEntityTypeBuilder.create(
            (pos, state) -> function.apply(new BlockPos(pos), new BlockState(state)),
            (net.minecraft.world.level.block.Block[])HolderBase.convertArray(blockSuppliers, net.minecraft.world.level.block.Block[]::new)
         )
         .build(null);
      this.objectsToRegister
         .add(
            () -> net.minecraft.core.Registry.register(
               BuiltInRegistries.BLOCK_ENTITY_TYPE, (net.minecraft.resources.Identifier)identifier.data, blockEntityType
            )
         );
      return new BlockEntityTypeRegistryObject<>(new org.mtr.mapping.holder.BlockEntityType<>(blockEntityType));
   }

   @MappedMethod
   public <T extends EntityExtension> EntityTypeRegistryObject<T> registerEntityType(
      Identifier identifier, BiFunction<EntityType<?>, World, T> function, float width, float height
   ) {
      net.minecraft.world.entity.EntityType<T> entityType = net.minecraft.world.entity.EntityType.Builder.of(this.getEntityFactory(function), MobCategory.MISC)
         .sized(width, height)
         .build(ResourceKey.create(Registries.ENTITY_TYPE, (net.minecraft.resources.Identifier)identifier.data));
      this.objectsToRegister
         .add(() -> net.minecraft.core.Registry.register(BuiltInRegistries.ENTITY_TYPE, (net.minecraft.resources.Identifier)identifier.data, entityType));
      return new EntityTypeRegistryObject<>(new EntityType<>(entityType));
   }

   private <T extends EntityExtension> EntityFactory<T> getEntityFactory(BiFunction<EntityType<?>, World, T> function) {
      return (entityType, world) -> function.apply(new EntityType(entityType), new World(world));
   }

   @MappedMethod
   public ParticleTypeRegistryObject registerParticleType(Identifier identifier) {
      return this.registerParticleType(identifier, false);
   }

   @MappedMethod
   public ParticleTypeRegistryObject registerParticleType(Identifier identifier, boolean alwaysSpawn) {
      DefaultParticleType defaultParticleType = new DefaultParticleType(FabricParticleTypes.simple(alwaysSpawn));
      this.objectsToRegister
         .add(
            () -> net.minecraft.core.Registry.register(
               BuiltInRegistries.PARTICLE_TYPE, (net.minecraft.resources.Identifier)identifier.data, (SimpleParticleType)defaultParticleType.data
            )
         );
      return new ParticleTypeRegistryObject(defaultParticleType, identifier);
   }

   @MappedMethod
   public CreativeModeTabHolder createCreativeModeTabHolder(Identifier identifier, Supplier<ItemStack> iconSupplier) {
      CreativeModeTab itemGroup = FabricCreativeModeTab.builder()
         .icon(() -> (net.minecraft.world.item.ItemStack)iconSupplier.get().data)
         .title(
            Component.translatable(
               String.format(
                  "itemGroup.%s.%s",
                  ((net.minecraft.resources.Identifier)identifier.data).getNamespace(),
                  ((net.minecraft.resources.Identifier)identifier.data).getPath()
               )
            )
         )
         .build();
      this.objectsToRegister
         .add(() -> net.minecraft.core.Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, (net.minecraft.resources.Identifier)identifier.data, itemGroup));
      return new CreativeModeTabHolder(itemGroup, (net.minecraft.resources.Identifier)identifier.data);
   }

   @MappedMethod
   public SoundEventRegistryObject registerSoundEvent(Identifier identifier) {
      SoundEvent soundEvent = SoundEvent.createVariableRangeEvent((net.minecraft.resources.Identifier)identifier.data);
      this.objectsToRegister
         .add(() -> net.minecraft.core.Registry.register(BuiltInRegistries.SOUND_EVENT, (net.minecraft.resources.Identifier)identifier.data, soundEvent));
      return new SoundEventRegistryObject(new org.mtr.mapping.holder.SoundEvent(soundEvent));
   }

   @MappedMethod
   public void registerCommand(String command, Consumer<CommandBuilder<?>> buildCommand, String... redirects) {
      this.commandsToRegister.add(dispatcher -> {
         CommandBuilder<LiteralArgumentBuilder<CommandSourceStack>> commandBuilder = new CommandBuilder(Commands.literal(command));
         buildCommand.accept(commandBuilder);
         LiteralCommandNode<CommandSourceStack> literalCommandNode = dispatcher.register(commandBuilder.argumentBuilder);

         for (String redirect : redirects) {
            dispatcher.register((LiteralArgumentBuilder)Commands.literal(redirect).redirect(literalCommandNode));
         }
      });
   }

   @MappedMethod
   public void setupPackets(Identifier identifier) {
      this.packetsIdentifier = identifier;
      ServerPlayNetworking.registerGlobalReceiver(
         RawBytePayload.ensureRegistered((net.minecraft.resources.Identifier)identifier.data),
         (payload, context) -> PacketBufferReceiver.receive(payload.toByteBuf(), packetBufferReceiver -> {
            Function<PacketBufferReceiver, ? extends PacketHandler> getInstance = this.packets.get(packetBufferReceiver.readString());
            if (getInstance != null) {
               getInstance.apply(packetBufferReceiver).runServer(new MinecraftServer(context.server()), new ServerPlayerEntity(context.player()));
            }
         }, context.server()::execute)
      );
   }

   @MappedMethod
   public <T extends PacketHandler> void registerPacket(Class<T> classObject, Function<PacketBufferReceiver, T> getInstance) {
      this.packets.put(classObject.getName(), getInstance);
   }

   @MappedMethod
   public <T extends PacketHandler> void sendPacketToClient(ServerPlayerEntity serverPlayerEntity, T data) {
      if (this.packetsIdentifier != null && serverPlayerEntity != null && serverPlayerEntity.data != null) {
         ServerPlayer player = (ServerPlayer)serverPlayerEntity.data;
         if (player.level() != null && player.level().getServer() != null) {
            PacketBufferSender packetBufferSender = new PacketBufferSender(Unpooled::buffer);
            packetBufferSender.writeString(data.getClass().getName());
            data.write(packetBufferSender);
            packetBufferSender.send(
               byteBuf -> {
                  if (serverPlayerEntity.data != null) {
                     ServerPlayNetworking.send(
                        (ServerPlayer)serverPlayerEntity.data,
                        new RawBytePayload((net.minecraft.resources.Identifier)this.packetsIdentifier.data, RawBytePayload.fromByteBuf(byteBuf))
                     );
                  }
               },
               runnable -> {
                  if (serverPlayerEntity.data != null
                     && ((ServerPlayer)serverPlayerEntity.data).level() != null
                     && ((ServerPlayer)serverPlayerEntity.data).level().getServer() != null) {
                     ((ServerPlayer)serverPlayerEntity.data).level().getServer().execute(runnable);
                  }
               }
            );
         }
      }
   }
}
