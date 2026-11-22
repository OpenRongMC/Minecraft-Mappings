package org.mtr.mapping.mapper;

import com.mojang.serialization.MapCodec;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mtr.mapping.holder.ClientWorld;
import org.mtr.mapping.registry.RegistryClient;

public record LegacyModelPredicate(Identifier predicate) implements RangeSelectItemModelProperty {
   public static final MapCodec<LegacyModelPredicate> CODEC = Identifier.CODEC
      .fieldOf("predicate")
      .xmap(LegacyModelPredicate::new, LegacyModelPredicate::predicate);
   private static final Map<Item, Map<Identifier, RegistryClient.ModelPredicateProvider>> PROVIDERS = new HashMap<>();

   public static void register(Item item, Identifier predicate, RegistryClient.ModelPredicateProvider provider) {
      PROVIDERS.computeIfAbsent(item, unused -> new HashMap<>()).put(predicate, provider);
   }

   public float get(ItemStack stack, ClientLevel level, ItemOwner owner, int seed) {
      Map<Identifier, RegistryClient.ModelPredicateProvider> providers = PROVIDERS.get(stack.getItem());
      RegistryClient.ModelPredicateProvider provider = providers == null ? null : providers.get(this.predicate);
      if (provider == null) {
         return 0.0F;
      } else {
         LivingEntity livingEntity = owner == null ? null : owner.asLivingEntity();
         return provider.call(
            new org.mtr.mapping.holder.ItemStack(stack.copy()),
            level == null ? null : new ClientWorld(level),
            livingEntity == null ? null : new org.mtr.mapping.holder.LivingEntity(livingEntity)
         );
      }
   }

   public MapCodec<LegacyModelPredicate> type() {
      return CODEC;
   }

   static {
      RangeSelectItemModelProperties.ID_MAPPER.put(Identifier.fromNamespaceAndPath("mtr", "legacy_predicate"), CODEC);
   }
}
