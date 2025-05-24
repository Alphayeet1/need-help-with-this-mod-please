package com.playerfixer.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class HotbarSnapshot {
    public static List<String> savedHotbar = new ArrayList<>();

    public static void saveHotbar() {
        MinecraftClient client = MinecraftClient.getInstance();
        savedHotbar.clear();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = client.player.getInventory().getStack(i);
            savedHotbar.add(stack.getItem().toString());
        }
        System.out.println("[PlayerFixer] Saved hotbar layout.");
    }

    public static void organizeHotbar() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Copy of savedHotbar
        List<String> layout = new ArrayList<>(savedHotbar);
        if (layout.size() != 9) {
            System.out.println("[PlayerFixer] No valid layout saved.");
            return;
        }

        for (int targetSlot = 0; targetSlot < 9; targetSlot++) {
            String targetItemId = layout.get(targetSlot);

            // Skip if already correct
            ItemStack current = client.player.getInventory().getStack(targetSlot);
            if (current.getItem().toString().equals(targetItemId)) continue;

            // Search entire inventory for a matching item
            for (int i = 9; i < client.player.getInventory().size(); i++) {
                ItemStack invStack = client.player.getInventory().getStack(i);
                if (invStack.getItem().toString().equals(targetItemId)) {
                    client.interactionManager.clickSlot(
                        0, // containerId for player inventory
                        i, // from inventory
                        targetSlot, // hotbar slot
                        2, // clickType = swap
                        client.player
                    );
                    break;
                }
            }
        }

        System.out.println("[PlayerFixer] Hotbar reorganized to saved layout.");
    }
}