package net.gobies.additions.item.armor;

import net.gobies.additions.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class SteelArmor extends ArmorItem {
    public SteelArmor(AdditionsArmorMaterials steel, Type type, Properties properties) {
        super(AdditionsArmorMaterials.STEEL, type, properties);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getSource().is(DamageTypes.LAVA) ||
                    event.getSource().is(DamageTypes.IN_FIRE) ||
                    event.getSource().is(DamageTypes.ON_FIRE) ||
                    event.getSource().is(DamageTypes.HOT_FLOOR)) {

                float pieces = 0;

                if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ArmorItem helmet &&
                        helmet.getMaterial() == AdditionsArmorMaterials.STEEL &&
                        helmet.getType() == ArmorItem.Type.HELMET) {
                    pieces++;
                }
                if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem chestplate &&
                        chestplate.getMaterial() == AdditionsArmorMaterials.STEEL &&
                        chestplate.getType() == ArmorItem.Type.CHESTPLATE) {
                    pieces++;
                }
                if (player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ArmorItem leggings &&
                        leggings.getMaterial() == AdditionsArmorMaterials.STEEL &&
                        leggings.getType() == ArmorItem.Type.LEGGINGS) {
                    pieces++;
                }
                if (player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArmorItem boots &&
                        boots.getMaterial() == AdditionsArmorMaterials.STEEL &&
                        boots.getType() == ArmorItem.Type.BOOTS) {
                    pieces++;
                }
                event.setAmount((float) (event.getAmount() * (1.0f - (pieces * Config.STEEL_ARMOR_DAMAGE_REDUCTION.get()))));
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.literal("Fire Damage Reduction: §3" + (Config.STEEL_ARMOR_DAMAGE_REDUCTION.get() * 100) + "%").withStyle(ChatFormatting.GRAY));
    }
}