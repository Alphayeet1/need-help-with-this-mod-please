package com.playerfixer.combat;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class AutoShieldHandler {
    private static final double DETECTION_RANGE = 5.0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null || client.currentScreen != null) return;

            for (Entity entity : client.world.getEntities()) {
                if (!(entity instanceof LivingEntity target)) continue;
                if (target.isDead() || target == client.player) continue;
                if (target.squaredDistanceTo(client.player) > DETECTION_RANGE * DETECTION_RANGE) continue;

                // Check if they are looking at us / likely to attack
                Vec3d toPlayer = client.player.getPos().subtract(target.getPos()).normalize();
                Vec3d attackerFacing = target.getRotationVec(1.0F);

                double dot = toPlayer.dotProduct(attackerFacing);
                boolean isAggroFacing = dot > 0.6; // Roughly aiming at us

                if (isAggroFacing) {
                    // Switch to shield
                    int shieldSlot = findShieldSlot(client.player.getInventory().main);
                    if (shieldSlot != -1) {
                        client.player.getInventory().selectedSlot = shieldSlot;

                        // Look at attacker
                        client.player.lookAt(entity.getCameraBlockPos());

                        // Hold right-click to raise shield
                        client.options.useKey.setPressed(true);

                        System.out.println("[PlayerFixer] Auto-shield activated!");
                        return;
                    }
                }
            }

            // If no threat detected, release right-click
            client.options.useKey.setPressed(false);
        });
    }

    private static int findShieldSlot(net.minecraft.inventory.SimpleInventory inv) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inv.getStack(i);
            if (stack.getItem() == Items.SHIELD) return i;
        }
        return -1;

               if (!PlayerFixerConfig.autoShield) return;
    }
}