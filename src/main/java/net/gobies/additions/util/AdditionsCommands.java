package net.gobies.additions.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.gobies.additions.Config;
import net.gobies.additions.world.MobRandomHP;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AdditionsCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> additionsCommand = Commands.literal("additions").requires(source -> source.hasPermission(2))
                .then(Commands.literal("summon")
                        .then(Commands.argument("entity", ResourceLocationArgument.id())
                                .suggests(AdditionsCommands::suggestEntities)
                                .then(Commands.argument("rarity", StringArgumentType.word())
                                        .suggests(AdditionsCommands::suggestRarities)
                                        .executes(AdditionsCommands::execute))));

        dispatcher.register(additionsCommand);
    }

    // Needs work
    public static int execute(CommandContext<CommandSourceStack> context) {
        ResourceLocation entityLocation = ResourceLocationArgument.getId(context, "entity");
        String entityName = entityLocation.toString();

        String rarityName = StringArgumentType.getString(context, "rarity");
        MobUtils.MobRarity rarity = MobUtils.MobRarity.valueOf(rarityName.toUpperCase());
        EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityLocation);

        if (entityType == null) {
            context.getSource().sendFailure(Component.literal("Entity not found"));
            return 0;
        }

        try {
            Entity entity = entityType.create(context.getSource().getLevel());
            if (entity == null) {
                context.getSource().sendFailure(Component.literal("Failed to create entity"));
                return 0;
            }

            if (entity instanceof LivingEntity livingEntity) {
                CommandSourceStack source = context.getSource();
                if (source.getEntity() instanceof LivingEntity player) {
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();

                    entity.setPos(x, y, z);

                    float bonusHealth = MobRandomHP.getBonusHealthForRarity(rarity, livingEntity);

                    CompoundTag entityTag = entity.getPersistentData();
                    entityTag.putString("Rarity", rarity.name());
                    entityTag.putFloat("BonusHealth", bonusHealth);

                    double currentBaseHealth = Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).getBaseValue();
                    Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(currentBaseHealth + bonusHealth);
                    livingEntity.setHealth(livingEntity.getMaxHealth());

                    context.getSource().getLevel().addFreshEntity(entity);
                    if (Config.MOB_RARITY_DISPLAY_NAME.get()) {
                        MobUtils.setMobNameWithRarity(livingEntity, rarity);

                    }
                    return 1;
                } else {
                    context.getSource().sendFailure(Component.literal("Command source is not a LivingEntity"));
                    return 0;
                }
            } else {
                context.getSource().sendFailure(Component.literal("Entity is not a LivingEntity"));
                return 0;
            }
        } catch (Exception e) {
            context.getSource().sendFailure(Component.literal("Failed to create entity: " + e.getMessage()));
            return 0;
        }
    }

    private static CompletableFuture<Suggestions> suggestEntities(CommandContext<CommandSourceStack> commandSourceStackCommandContext, SuggestionsBuilder builder) {
        for (EntityType<?> entityType : ForgeRegistries.ENTITY_TYPES.getValues()) {
            try {
                Entity entity = entityType.create(commandSourceStackCommandContext.getSource().getLevel());
                if (entity instanceof LivingEntity) {
                    ResourceLocation registryName = ForgeRegistries.ENTITY_TYPES.getKey(entityType);
                    if (registryName != null) {
                        builder.suggest(registryName.toString());
                    }
                }
            } catch (Exception e) {
                // Handle exception if the entity cannot be created
            }
        }
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> suggestRarities(CommandContext<CommandSourceStack> commandSourceStackCommandContext, SuggestionsBuilder builder) {
        builder.suggest(MobUtils.MobRarity.COMMON.name().toLowerCase());
        builder.suggest(MobUtils.MobRarity.UNCOMMON.name().toLowerCase());
        builder.suggest(MobUtils.MobRarity.RARE.name().toLowerCase());
        builder.suggest(MobUtils.MobRarity.EPIC.name().toLowerCase());
        builder.suggest(MobUtils.MobRarity.LEGENDARY.name().toLowerCase());
        builder.suggest(MobUtils.MobRarity.SHINY.name().toLowerCase());
        return builder.buildFuture();
    }
}