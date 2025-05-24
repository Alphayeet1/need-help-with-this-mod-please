package com.playerfixer.logic;

import com.playerfixer.config.PlayerFixerConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;

public class TeamChecker {

    /**
     * Returns true if the target is on the same team as the player.
     */
    public static boolean isTeammate(Entity target) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (!(target instanceof PlayerEntity) || client.player == null) return false;

        Team playerTeam = client.player.getScoreboardTeam();
        Team targetTeam = ((PlayerEntity) target).getScoreboardTeam();

        return playerTeam != null && playerTeam.equals(targetTeam);
    }

    /**
     * Returns true if the target should be ignored based on username or team.
     */
    public static boolean shouldIgnore(Entity target) {
        if (!(target instanceof PlayerEntity)) return false;

        String targetName = target.getName().getString();

        // Check no-attack list
        if (PlayerFixerConfig.noAttackPlayers.contains(targetName)) {
            return true;
        }

        // Check team
        return isTeammate(target);
    }
}