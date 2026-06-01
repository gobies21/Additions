package net.gobies.additions.item.supplies;

import net.gobies.additions.util.MobUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RarityScannerItem extends Item {
    public RarityScannerItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack pStack, @NotNull Player pPlayer, @NotNull LivingEntity pEntity, @NotNull InteractionHand pUsedHand) {
        if (!pPlayer.level().isClientSide()) {
            String mobRarity = MobUtils.getMobRarity(pEntity);
            float bonusHealth = MobUtils.getBonusHealth(pEntity);
            MutableComponent entityName = (MutableComponent) pEntity.getDisplayName();
            MutableComponent name = Component.translatable(entityName.getString());

            pPlayer.displayClientMessage(Component.literal(name.getString() + " Rarity: " + mobRarity + ", Bonus Health: " + bonusHealth).withStyle(ChatFormatting.LIGHT_PURPLE), true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.additions.rarity_scanner.desc").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}