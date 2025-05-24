package com.playerfixer.logic;

import net.minecraft.client.MinecraftClient;

public class FallAnalyzer {
    private static final float DENSITY_THRESHOLD = 7.0f;

    public static boolean useBreachMace() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return false;

        float fall = client.player.fallDistance;

        // Use Breach if jumping or falling up to 7 blocks
        return fall <= DENSITY_THRESHOLD && !client.player.isFallFlying();
    }

    public static boolean useDensityMace() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return false;

        float fall = client.player.fallDistance;

        // Use Density if falling hard or gliding
        return fall > DENSITY_THRESHOLD || client.player.isFallFlying();
    }
}