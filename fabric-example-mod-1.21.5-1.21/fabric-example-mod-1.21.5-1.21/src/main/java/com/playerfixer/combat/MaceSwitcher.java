package com.playerfixer.combat;

import com.playerfixer.config.PlayerFixerConfig;
import com.playerfixer.util.HotbarUtils;
import com.playerfixer.util.WeaponUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public class MaceSwitcher {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            System.out.println("[PlayerFixer] Running tick test...");
        });


        if (!client.options.attackKey.isPressed()) return;
            if (client.player == null || client.currentScreen != null) return;

            ItemStack held = client.player.getMainHandStack();
            if (!WeaponUtils.isSwordOrMace(held)) return;

            boolean isFalling = client.player.fallDistance > 2.0F;
            boolean usingElytra = client.player.isFallFlying();

            ItemStack bestMace = HotbarUtils.findBestMace(isFalling || usingElytra);
            if (bestMace != null) {
                HotbarUtils.switchTo(bestMace);
            }
        });
    }
}
