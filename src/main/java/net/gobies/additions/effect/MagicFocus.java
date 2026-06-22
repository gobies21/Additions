package net.gobies.additions.effect;

import net.gobies.additions.compat.easymagic.EasyMagicCompat;
import net.gobies.additions.init.AdditionsAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class MagicFocus extends MobEffect {
    private static final UUID MAGIC_FOCUS = UUID.fromString("ff23f214-1280-4eb2-8d19-1a1e030c1f5c");

    public MagicFocus(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity livingEntity, @NotNull AttributeMap attributeMap, int amplifier) {
        this.getAttributeModifiers().put(AdditionsAttributes.ENCHANTING_FOCUS.get(), createModifier());
        super.addAttributeModifiers(livingEntity, attributeMap, amplifier);
        EasyMagicCompat.refreshMenu(livingEntity);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity livingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(livingEntity, pAttributeMap, pAmplifier);
        var attributeInstance = pAttributeMap.getInstance(AdditionsAttributes.ENCHANTING_FOCUS.get());
        if (attributeInstance != null) {
            attributeInstance.removeModifier(MAGIC_FOCUS);
        }
        EasyMagicCompat.refreshMenu(livingEntity);
    }

    @Override
    public @NotNull Map<Attribute, AttributeModifier> getAttributeModifiers() {
        Map<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers();
        modifiers.put(AdditionsAttributes.ENCHANTING_FOCUS.get(), createModifier());
        return modifiers;
    }

    private AttributeModifier createModifier() {
        return new AttributeModifier(MAGIC_FOCUS, this::getDescriptionId, 1, AttributeModifier.Operation.ADDITION);
    }
}