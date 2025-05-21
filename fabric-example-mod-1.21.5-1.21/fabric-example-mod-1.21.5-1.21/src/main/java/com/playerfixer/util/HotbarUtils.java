package com.playerfixer.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HotbarUtils {

    public static void switchTo(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        for (int i = 0; i < 9; i++) {
            if (ItemStack.areEqual(client.player.getInventory().getStack(i), stack)) {
                client.player.getInventory().selectedSlot = i;
                break;
            }
        }
    }

    public static ItemStack findBestMace(boolean highFall) {
        MinecraftClient client = MinecraftClient.getInstance();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = client.player.getInventory().getStack(i);
            if (highFall && WeaponUtils.isDensityMace(stack)) return stack;
            if (!highFall && WeaponUtils.isBreachMace(stack)) return stack;
        }
        return null;
    }

    public static ItemStack findItem(Class<? extends Item> itemType) {
        MinecraftClient client = MinecraftClient.getInstance();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = client.player.getInventory().getStack(i);
            if (itemType.isInstance(stack.getItem().getClass())) {
                return stack;
            }
        }
        return null;
    }
}
