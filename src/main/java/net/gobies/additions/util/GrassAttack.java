package net.gobies.additions.util;

import net.gobies.additions.Config;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class GrassAttack {

    public GrassAttack() {
    }

    @SubscribeEvent
    public static void onAttack(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getLevel().getBlockState(event.getPos()).getCollisionShape(event.getLevel(), event.getPos()).isEmpty() && event.getEntity() != null) {

            var player = event.getEntity();
            var itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (Config.GRASS_ATTACK.get()) {
                if (itemStack.canPerformAction(ToolActions.SWORD_SWEEP)) {
                    var attackReach = player.getAttributeValue(ForgeMod.ENTITY_REACH.get());
                    if (player.isCreative()) {
                        attackReach = 5.0D;
                    }
                    AABB bounds = player.getBoundingBox().inflate(attackReach);
                    List<Entity> entities = player.level().getEntities(player, bounds);

                    LivingEntity target = null;
                    double nearestDistance = Double.MAX_VALUE;
                    var viewVector = player.getViewVector(1.0F);
                    var eyePosition = player.getEyePosition();
                    var multipliedReach = player.isCreative() ? attackReach * 2 : attackReach;
                    var lookVec = eyePosition.add(viewVector.x * multipliedReach, viewVector.y * multipliedReach, viewVector.z * multipliedReach);

                    for (Entity entity : entities) {
                        if (entity instanceof LivingEntity living && entity != player) {
                            var box = entity.getBoundingBox();
                            var hit = box.clip(eyePosition, lookVec).orElse(null);
                            if (hit != null) {
                                double distance = player.distanceToSqr(entity);
                                if (distance < nearestDistance) {
                                    nearestDistance = distance;
                                    target = living;
                                }
                            }
                        }
                    }
                    if (target != null) {
                        player.attack(target);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}