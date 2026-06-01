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
    public static final RegistryObject<SoundEvent> PLAYER_BOW_PULL;
    public static final RegistryObject<SoundEvent> PLAYER_BOW_SHOOT;
    public static final RegistryObject<SoundEvent> PLAYER_CROSSBOW_SHOOT;
    public static final RegistryObject<SoundEvent> PLAYER_CROSSBOW_PULL;
    public static final RegistryObject<SoundEvent> PLAYER_BOW_EQUIP;
    public static final RegistryObject<SoundEvent> PLAYER_OTHER_EQUIP;
    public static final RegistryObject<SoundEvent> PLAYER_CRAFT_OTHER;
    public static final RegistryObject<SoundEvent> PLAYER_CRAFT_WOOD;
    public static final RegistryObject<SoundEvent> PLAYER_SWORD_SLASH;
    public static final RegistryObject<SoundEvent> PLAYER_CRIT_HIT;

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Additions.MOD_ID);
        PLAYER_SWORD_SWING = REGISTRY.register("player_sword_swing", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_sword_swing")));
        PLAYER_SWORD_EQUIP = REGISTRY.register("player_sword_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_sword_equip")));
        PLAYER_TOOL_EQUIP = REGISTRY.register("player_tool_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_tool_equip")));
        PLAYER_TOOL_SWING= REGISTRY.register("player_tool_swing", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_tool_swing")));
        PLAYER_BOW_PULL = REGISTRY.register("player_bow_pull", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_bow_pull")));
        PLAYER_BOW_SHOOT = REGISTRY.register("player_bow_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_bow_shoot")));
        PLAYER_CROSSBOW_SHOOT = REGISTRY.register("player_crossbow_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_crossbow_shoot")));
        PLAYER_CROSSBOW_PULL = REGISTRY.register("player_crossbow_pull", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_crossbow_pull")));
        PLAYER_BOW_EQUIP = REGISTRY.register("player_bow_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_bow_equip")));
        PLAYER_OTHER_EQUIP = REGISTRY.register("player_other_equip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_other_equip")));
        PLAYER_CRAFT_OTHER = REGISTRY.register("player_craft_other", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_craft_other")));
        PLAYER_CRAFT_WOOD = REGISTRY.register("player_craft_wood", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_craft_wood")));
        PLAYER_SWORD_SLASH = REGISTRY.register("player_sword_slash", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_sword_slash")));
        PLAYER_CRIT_HIT = REGISTRY.register("player_crit_hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Additions.MOD_ID, "player_crit_hit")));

    }
}