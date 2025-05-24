package com.playerfixer.combat;

import com.playerfixer.config.PlayerFixerConfig;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartTick;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class RightClickHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient || hand != player.getActiveHand()) return ActionResult.PASS;

            if (player.getMainHandStack().getItem() == Items.TOTEM_OF_UNDYING && entity instanceof PlayerEntity target) {
                String name = target.getName().getString();

                if (PlayerFixerConfig.noAttackPlayers.add(name)) {
                    player.sendMessage(Text.literal("[PlayerFixer] Added " + name + " to No-Attack List."), true);
                } else {
                    player.sendMessage(Text.literal("[PlayerFixer] " + name + " is already in No-Attack List."), true);
                }

                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}