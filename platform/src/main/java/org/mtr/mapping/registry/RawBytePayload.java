package org.mtr.mapping.registry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.resources.Identifier;

public final class RawBytePayload implements CustomPacketPayload {
   private static final Map<Identifier, Type<RawBytePayload>> TYPES = new HashMap<>();
   private final Type<RawBytePayload> type;
   private final byte[] bytes;

   public RawBytePayload(Identifier identifier, byte[] bytes) {
      this.type = ensureRegistered(identifier);
      this.bytes = bytes;
   }

   private RawBytePayload(Type<RawBytePayload> type, FriendlyByteBuf buf) {
      this.type = type;
      this.bytes = new byte[buf.readableBytes()];
      buf.readBytes(this.bytes);
   }

   private void write(FriendlyByteBuf buf) {
      buf.writeBytes(this.bytes);
   }

   public FriendlyByteBuf toByteBuf() {
      return new FriendlyByteBuf(Unpooled.wrappedBuffer(this.bytes));
   }

   public static byte[] fromByteBuf(ByteBuf byteBuf) {
      byte[] data = new byte[byteBuf.readableBytes()];
      byteBuf.getBytes(byteBuf.readerIndex(), data);
      return data;
   }

   public static synchronized Type<RawBytePayload> ensureRegistered(Identifier identifier) {
      Type<RawBytePayload> existingType = TYPES.get(identifier);
      if (existingType != null) {
         return existingType;
      } else {
         Type<RawBytePayload> type = new Type(identifier);
         StreamCodec<FriendlyByteBuf, RawBytePayload> codec = CustomPacketPayload.codec(RawBytePayload::write, buffer -> new RawBytePayload(type, buffer));
         PayloadTypeRegistry.serverboundPlay().register(type, codec);
         PayloadTypeRegistry.clientboundPlay().register(type, codec);
         TYPES.put(identifier, type);
         return type;
      }
   }

   public Type<? extends CustomPacketPayload> type() {
      return this.type;
   }
}
