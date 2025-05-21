package com.playerfixer;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import com.playerfixer.config.PlayerFixerConfig;
import com.playerfixer.combat.MaceSwitcher;
import com.playerfixer.combat.ShieldBreakHandler;
import com.playerfixer.combat.AutoAimHandler;

public class PlayerFixerMod implements ClientModInitializer {
    public static final String MOD_ID = "playerfixer";

    @Override
    public void onInitializeClient() {
        System.out.println("[PlayerFixer] Initializing..."); // debug line
        PlayerFixerConfig.init();
        MaceSwitcher.register();
        AutoAimHandler.register();
        ShieldBreakHandler.register();
    }

}
