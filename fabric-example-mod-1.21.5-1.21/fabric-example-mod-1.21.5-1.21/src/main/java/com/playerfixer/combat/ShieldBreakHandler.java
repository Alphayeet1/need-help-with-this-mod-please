package com.playerfixer.combat;

import com.playerfixer.config.PlayerFixerConfig;
import com.playerfixer.util.HotbarUtils;
import com.playerfixer.util.WeaponUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

public class ShieldBreakHandler {
    private static boolean shieldBroken = false;
    private static int followUpTimer = 0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> { ... });
            if (PlayerFixerConfig.globalPause || !PlayerFixerConfig.shieldBreaker) return;

            if (!client.options.attackKey.isPressed()) return;

            if (client.player == null || client.currentScreen != null || !(client.targetedEntity instanceof LivingEntity)) return;

            LivingEntity target = (LivingEntity) client.targetedEntity;
            if (!target.isBlocking()) return;

            if (WeaponUtils.isSwordOrMace(client.player.getMainHandStack())) {
                // Switch to axe to break shield
                ItemStack axe = HotbarUtils.findItem(AxeItem.class);
                if (axe != null) {
                    HotbarUtils.switchTo(axe);
                    shieldBroken = true;
                    followUpTimer = 6; // wait a short moment before follow-up
                }
            }

            // Follow-up attack
            if (shieldBroken && PlayerFixerConfig.maceAfterShield) {
                if (--followUpTimer <= 0) {
                    ItemStack bestMace = HotbarUtils.findBestMace(true);
                    if (bestMace != null) {
                        HotbarUtils.switchTo(bestMace);
                    }
                    shieldBroken = false;
                }
            }
        });
    }
}
