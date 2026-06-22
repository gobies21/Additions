package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY;
    public static final RegistryObject<SoundEvent> PLAYER_SWORD_SWING;
    public static final RegistryObject<SoundEvent> PLAYER_SWORD_EQUIP;
    public static final RegistryObject<SoundEvent> PLAYER_TOOL_EQUIP;
    public static final RegistryObject<SoundEvent> PLAYER_TOOL_SWING;
    public static final RegistryObject<SoundEvent> BOW_PULL;
    public static final RegistryObject<SoundEvent> BOW_SHOOT;
    public static final RegistryObject<SoundEvent> CROSSBOW_SHOOT;
    public static final RegistryObject<SoundEvent> CROSSBOW_PULL;
    public static final RegistryObject<SoundEvent> PLAYER_BOW_EQUIP;
    public static final RegistryObject<SoundEvent> PLAYER_OTHER_EQUIP;
    public static final RegistryObject<SoundEvent> PLAYER_CRAFT_OTHER;
    public static final RegistryObject<SoundEvent> PLAYER_CRAFT_WOOD;
    public static final RegistryObject<SoundEvent> PLAYER_SWORD_SLASH;
    public static final RegistryObject<SoundEvent> PLAYER_CRIT_HIT;
    public static final RegistryObject<SoundEvent> PLAYER_JUMP;
    public static final RegistryObject<SoundEvent> POTION;
    public static final RegistryObject<SoundEvent> ARMOR_CRYSTAL_RUN;
    public static final RegistryObject<SoundEvent> ARMOR_CRYSTAL_WALK;
    public static final RegistryObject<SoundEvent> ARMOR_HEAVY_RUN;
    public static final RegistryObject<SoundEvent> ARMOR_HEAVY_WALK;
    public static final RegistryObject<SoundEvent> ARMOR_LIGHT;
    public static final RegistryObject<SoundEvent> ARMOR_MEDIUM;
    public static final RegistryObject<SoundEvent> ARMOR_SLIME_RUN;
    public static final RegistryObject<SoundEvent> ARMOR_SLIME_WALK;

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }

    public static SoundEvent getOrFallback(RegistryObject<SoundEvent> registryObject, SoundEvent fallback) {
        return registryObject.isPresent() ? registryObject.get() : fallback;
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Additions.MOD_ID);
        PLAYER_SWORD_SWING = REGISTRY.register("player_sword_swing", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_sword_swing")));
        PLAYER_SWORD_EQUIP = REGISTRY.register("player_sword_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_sword_equip")));
        PLAYER_TOOL_EQUIP = REGISTRY.register("player_tool_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_tool_equip")));
        PLAYER_TOOL_SWING= REGISTRY.register("player_tool_swing", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_tool_swing")));
        BOW_PULL = REGISTRY.register("bow_pull", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "bow_pull")));
        BOW_SHOOT = REGISTRY.register("bow_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "bow_shoot")));
        CROSSBOW_SHOOT = REGISTRY.register("crossbow_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "crossbow_shoot")));
        CROSSBOW_PULL = REGISTRY.register("crossbow_pull", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "crossbow_pull")));
        PLAYER_BOW_EQUIP = REGISTRY.register("player_bow_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_bow_equip")));
        PLAYER_OTHER_EQUIP = REGISTRY.register("player_other_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_other_equip")));
        PLAYER_CRAFT_OTHER = REGISTRY.register("player_craft_other", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_craft_other")));
        PLAYER_CRAFT_WOOD = REGISTRY.register("player_craft_wood", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_craft_wood")));
        PLAYER_SWORD_SLASH = REGISTRY.register("player_sword_slash", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_sword_slash")));
        PLAYER_CRIT_HIT = REGISTRY.register("player_crit_hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_crit_hit")));
        POTION = REGISTRY.register("potion", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "potion")));
        PLAYER_JUMP = REGISTRY.register("player_jump", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_jump")));
        ARMOR_CRYSTAL_RUN = REGISTRY.register("armor_crystal_run", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_crystal_run")));
        ARMOR_CRYSTAL_WALK = REGISTRY.register("armor_crystal_walk", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_crystal_walk")));
        ARMOR_HEAVY_RUN = REGISTRY.register("armor_heavy_run", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_heavy_run")));
        ARMOR_HEAVY_WALK = REGISTRY.register("armor_heavy_walk", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_heavy_walk")));
        ARMOR_LIGHT = REGISTRY.register("armor_light", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_light")));
        ARMOR_MEDIUM = REGISTRY.register("armor_medium", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_medium")));
        ARMOR_SLIME_RUN = REGISTRY.register("armor_slime_run", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_slime_run")));
        ARMOR_SLIME_WALK = REGISTRY.register("armor_slime_walk", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "armor_slime_walk")));
    }
}