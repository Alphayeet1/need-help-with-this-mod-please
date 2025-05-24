package com.playerfixer.config;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PlayerFixerConfig {
    public static boolean autoAim = true;
    public static boolean autoSwitch = true;
    public static boolean autoAttack = true;
    public static boolean stealthMode = false;
    public static boolean globalPause = false;
    public static boolean shieldBreaker = true;
    public static boolean maceAfterShield = true;
    public static boolean autoShield = true;

    public static Set<String> noAttackPlayers = new HashSet<>();

    public static KeyBinding togglePauseKey;

    public static void init() {
        // You can use a keybind registration library here if needed
        // Placeholder for keybind logic or JSON config handling
        public static float aimSpeed = 0.6f; // Default to 60% aim speed

    }

    public static File getConfigFile() {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), "playerfixer.json");
    }
}
