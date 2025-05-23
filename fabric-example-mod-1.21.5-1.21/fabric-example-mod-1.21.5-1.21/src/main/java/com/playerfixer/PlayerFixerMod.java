package com.playerfixer;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import com.playerfixer.config.PlayerFixerConfig;
import com.playerfixer.combat.MaceSwitcher;
import com.playerfixer.combat.ShieldBreakHandler;
import com.playerfixer.combat.AutoAimHandler;
import com.playerfixer.combat.RightClickHandler;
import com.playerfixer.combat.AutoShieldHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

ClientTickEvents.END_CLIENT_TICK.register(client -> {
    HotbarSnapshot.autoOrganizeIfMaceDetected();
});

public class PlayerFixerMod implements ClientModInitializer {
    public static final String MOD_ID = "playerfixer";

    @Override
    public void onInitializeClient() {
        System.out.println("[PlayerFixer] Mod is active!"); // debug line
        PlayerFixerConfig.init();
        MaceSwitcher.register();
        AutoAimHandler.register();
        ShieldBreakHandler.register();
        RightClickHandler.register();
        AutoShieldHandler.register();
    }

}
