package net.gobies.additions.mixin;

import net.gobies.additions.init.AdditionsRarities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Shadow @Final
    private Rarity rarity;

    @Redirect(
            method = "getRarity",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/Item;rarity:Lnet/minecraft/world/item/Rarity;",
                    opcode = Opcodes.GETFIELD)
    )
    private Rarity additions$legendaryRarity(Item instance, ItemStack stack) {
        if (!stack.isEnchanted()) {
            return this.rarity;
        }

        if (this.rarity == Rarity.EPIC) {
            return AdditionsRarities.LEGENDARY;
        }

        return this.rarity;
    }
}