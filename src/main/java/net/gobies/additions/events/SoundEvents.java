package net.gobies.additions.events;

import net.gobies.additions.Additions;
import net.gobies.additions.Config;
import net.gobies.additions.compat.ironsspellbooks.IronsSpellbooksCompat;
import net.gobies.additions.compat.pml.PMLCompat;
import net.gobies.additions.compat.spartanweaponry.SpartanWeaponryCompat;
import net.gobies.additions.init.AdditionsSounds;
import net.gobies.additions.util.AdditionsUtil;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEvents {
    /*
     * Code in this class is referenced from Immersive Tools And Weapons Sounds by shinguy under Eclipse Public License - v 2.0
     * Sounds used in this code originate from Dynamic Surroundings by OreCruncher under MIT License
     */
    private static final Map<UUID, Long> SWORD_ATTACK_COOLDOWN = new HashMap<>();
    private static final Map<UUID, Long> CRAFT_COOLDOWN = new HashMap<>();
    private static final long COOLDOWN = 500;
    private static final Map<UUID, Boolean> BOW_LAST_TICK = new HashMap<>();
    private static final Map<UUID, Boolean> CROSSBOW_TICK = new HashMap<>();
    private static final Map<UUID, Long> SWING_COOLDOWN = new HashMap<>();

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.getItem() instanceof TieredItem && (!(heldItem.getItem() instanceof DiggerItem)) || heldItem.getItem() instanceof TridentItem ||  AdditionsUtil.isValidWeapon(heldItem.getItem())) {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_SWORD_SWING.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.7F, 1.0F);
                if (PMLCompat.isCriticalHit(damageSource)) {
                    target.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRIT_HIT.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue() + 0.5F, 1.0F);
                } else {
                    target.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_SWORD_SLASH.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onCrit(CriticalHitEvent event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        if (entity instanceof LivingEntity) {
            if (event.isVanillaCritical()) {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRIT_HIT.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue() + 0.5F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onWeaponSwing(PlayerInteractEvent.LeftClickEmpty event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        Player player = event.getEntity();
        if (!player.level().isClientSide()) return;
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getItem() instanceof TieredItem && (!(heldItem.getItem() instanceof DiggerItem)) || heldItem.getItem() instanceof TridentItem || AdditionsUtil.isValidWeapon(heldItem.getItem())) {
            UUID playerUUID = player.getUUID();
            long currentTime = System.currentTimeMillis();
            Long lastAttackTime = SWORD_ATTACK_COOLDOWN.get(playerUUID);
            if (lastAttackTime != null && currentTime - lastAttackTime < COOLDOWN) {
                long remaining = COOLDOWN - (currentTime - lastAttackTime);
                System.out.println("[DEBUG] Sword Air Attack on cooldown: " + remaining + "ms remaining");
            } else {
                player.level().playSound(player, player.blockPosition(), AdditionsSounds.PLAYER_SWORD_SWING.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.7F, 1.0F);
                SWORD_ATTACK_COOLDOWN.put(playerUUID, currentTime);
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        if (event.phase != TickEvent.Phase.START) return;
        Player player = event.player;
        if (player.level().isClientSide()) {
            UUID playerUUID = player.getUUID();
            ItemStack heldItem = player.getMainHandItem();
            boolean isUsingBow = player.isUsingItem() && heldItem.getItem() instanceof BowItem;
            boolean wasUsingBowLastTick = BOW_LAST_TICK.getOrDefault(playerUUID, false);
            boolean isUsingCrossbow = player.isUsingItem() && heldItem.getItem() instanceof CrossbowItem;
            boolean wasUsingCrossbowLastTick = CROSSBOW_TICK.getOrDefault(playerUUID, false);
            if (isUsingBow && !wasUsingBowLastTick) {
                player.level().playSound(player, player.blockPosition(), AdditionsSounds.PLAYER_BOW_PULL.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
            } else if (isUsingCrossbow && !wasUsingCrossbowLastTick && !SpartanWeaponryCompat.isHeavyCrossbow(heldItem.getItem())) {
                player.level().playSound(player, player.blockPosition(), AdditionsSounds.PLAYER_CROSSBOW_PULL.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
            }
            BOW_LAST_TICK.put(playerUUID, isUsingBow);
            CROSSBOW_TICK.put(playerUUID, isUsingCrossbow);
        }
    }

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        Player player = event.getEntity();
        if (!player.level().isClientSide()) return;
        ItemStack weapon = event.getBow();
        if (weapon.getItem() instanceof BowItem) {
            player.level().playSound(player, player.blockPosition(), AdditionsSounds.PLAYER_BOW_SHOOT.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
        } else if (weapon.getItem() instanceof CrossbowItem || SpartanWeaponryCompat.isHeavyCrossbow(weapon.getItem())) {
            player.level().playSound(player, player.blockPosition(), AdditionsSounds.PLAYER_CROSSBOW_SHOOT.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
        }
    }

    @SubscribeEvent
    public static void onToolAttack(AttackEntityEvent event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        float attackStrength = player.getAttackStrengthScale(0.5F);
        if (heldItem.getItem() instanceof DiggerItem || IronsSpellbooksCompat.isStaff(heldItem.getItem()) || AdditionsUtil.isValidTool(heldItem.getItem())) {
            if (player.fallDistance > 0.1F && attackStrength >= 0.2F)
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_TOOL_SWING.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
            else if (attackStrength >= 0.2F) {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_TOOL_SWING.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onToolSwing(PlayerInteractEvent.LeftClickEmpty event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        Player player = event.getEntity();
        if (!player.level().isClientSide()) return;
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getItem() instanceof DiggerItem || IronsSpellbooksCompat.isStaff(heldItem.getItem()) || AdditionsUtil.isValidTool(heldItem.getItem())) {
            UUID playerUUID = player.getUUID();
            long currentTime = System.currentTimeMillis();
            Long lastAttackTime = SWING_COOLDOWN.get(playerUUID);
            if (lastAttackTime != null && currentTime - lastAttackTime < COOLDOWN) {
                long remaining = COOLDOWN - (currentTime - lastAttackTime);
                System.out.println("[DEBUG] Tool Air Attack on cooldown: " + remaining + "ms remaining");
            } else {
                player.level().playSound(player, player.blockPosition(), AdditionsSounds.PLAYER_TOOL_SWING.get(), SoundSource.PLAYERS, Config.SOUND_VOLUME.get().floatValue(), 1.0F);
                SWING_COOLDOWN.put(playerUUID, currentTime);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCraft(PlayerEvent.ItemCraftedEvent event) {
        if (!Config.ENABLE_SPECIAL_SOUNDS.get()) return;
        if (event.getCrafting().isEmpty()) return;
        Player player = event.getEntity();

        boolean hasWood = false;
        if (player.level().isClientSide()) return;
        long currentTime = System.currentTimeMillis();
        Predicate<ItemStack> isWood = stack ->
                stack.is(ItemTags.PLANKS) ||
                        stack.is(ItemTags.LOGS) ||
                        stack.is(ItemTags.WOODEN_SLABS) ||
                        stack.is(ItemTags.WOODEN_STAIRS) ||
                        stack.is(ItemTags.WOODEN_DOORS) ||
                        stack.is(ItemTags.WOODEN_TRAPDOORS) ||
                        stack.is(ItemTags.WOODEN_FENCES) ||
                        stack.is(ItemTags.WOODEN_BUTTONS) ||
                        stack.is(ItemTags.SIGNS) ||
                        stack.is(ItemTags.HANGING_SIGNS) ||
                        stack.is(ItemTags.BOATS) ||
                        stack.is(ItemTags.CHEST_BOATS) ||
                        stack.is(ItemTags.WOODEN_PRESSURE_PLATES);
        if (isWood.test(event.getCrafting())) {
            hasWood = true;
        }
        CraftingContainer container = (CraftingContainer) event.getInventory();
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty() && isWood.test(stack)) {
                hasWood = true;
                break;
            }
        }
        UUID playerUUID = player.getUUID();
        Long lastCraftTime = CRAFT_COOLDOWN.get(playerUUID);
        if (lastCraftTime == null || currentTime - lastCraftTime >= (COOLDOWN + 500)) {
            if (!hasWood) {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRAFT_OTHER.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
            } else {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRAFT_WOOD.get(), SoundSource.PLAYERS, (Config.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
            }
            CRAFT_COOLDOWN.put(playerUUID, currentTime);
        }
    }
}
