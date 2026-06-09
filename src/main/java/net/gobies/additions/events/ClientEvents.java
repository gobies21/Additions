package net.gobies.additions.events;

import net.gobies.additions.Additions;
import net.gobies.additions.Config;
import net.gobies.additions.compat.ironsspellbooks.IronsSpellbooksCompat;
import net.gobies.additions.init.AdditionsSounds;
import net.gobies.additions.util.AdditionsUtil;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    /*
     * Sounds used in this code originate from Dynamic Surroundings by OreCruncher under MIT License
    */
    private static final Map<UUID, Integer> lastSelectedSlot = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide()) return;

        int currentSlot = player.getInventory().selected;
        UUID playerUUID = player.getUUID();
        int lastSlot = lastSelectedSlot.getOrDefault(playerUUID, -1);

        if (currentSlot == lastSlot) return;
        ItemStack currentItem = player.getMainHandItem();

        if (currentItem.isEmpty()) {
            lastSelectedSlot.put(playerUUID, currentSlot);
            return;
        }
        boolean hasLight = false;
        boolean hasMedium = false;
        boolean hasHeavy = false;
        boolean hasCrystal = false;
        ItemStack armor = player.getMainHandItem();
        if (armor.getItem() instanceof ArmorItem armorItem) {
            ArmorMaterial material = armorItem.getMaterial();
            if (material == ArmorMaterials.LEATHER || AdditionsUtil.isValidLightArmor(armor.getItem())) hasLight = true;
            if (material == ArmorMaterials.CHAIN || AdditionsUtil.isValidMediumArmor(armor.getItem())) hasMedium = true;
            if (material == ArmorMaterials.IRON || material == ArmorMaterials.GOLD || material == ArmorMaterials.NETHERITE || AdditionsUtil.isValidHeavyArmor(armor.getItem())) hasHeavy = true;
            if (material == ArmorMaterials.DIAMOND || AdditionsUtil.isValidCrystalArmor(armor.getItem())) hasCrystal = true;
        }

        Item currentItemType = currentItem.getItem();
        if (currentItemType instanceof TieredItem && (!(currentItemType instanceof DiggerItem)) || currentItemType instanceof TridentItem || AdditionsUtil.isValidWeapon(currentItemType)) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_SWORD_EQUIP.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
        } else if (currentItemType instanceof BowItem || currentItemType instanceof CrossbowItem) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_BOW_EQUIP.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (currentItemType instanceof DiggerItem || IronsSpellbooksCompat.isStaff(currentItemType) || AdditionsUtil.isValidTool(currentItemType)) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_TOOL_EQUIP.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (currentItemType instanceof PotionItem) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.POTION.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (hasCrystal) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.ARMOR_CRYSTAL_WALK.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (hasLight) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.ARMOR_LIGHT.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (hasMedium) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.ARMOR_MEDIUM.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (hasHeavy) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.ARMOR_HEAVY_WALK.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (armor.getItem() instanceof ArmorItem) {
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.ARMOR_HEAVY_WALK.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        } else if (!currentItemType.getDefaultInstance().isEmpty()){
            player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_OTHER_EQUIP.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
        }

        lastSelectedSlot.put(playerUUID, currentSlot);
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        lastSelectedSlot.clear();
    }
}