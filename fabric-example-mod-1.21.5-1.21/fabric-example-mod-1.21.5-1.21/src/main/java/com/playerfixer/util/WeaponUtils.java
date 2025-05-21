package com.playerfixer.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class WeaponUtils {

    public static boolean isMace(ItemStack stack) {
        return stack.getItem().toString().toLowerCase().contains("mace");
    }

    public static boolean isBreachMace(ItemStack stack) {
        String id = stack.getItem().toString().toLowerCase();
        return id.contains("breach");
    }

    public static boolean isDensityMace(ItemStack stack) {
        String id = stack.getItem().toString().toLowerCase();
        return id.contains("density");
    }

    public static boolean isSword(ItemStack stack) {
        return stack.getItem().getTranslationKey().contains("sword") || stack.getItem() == Items.NETHERITE_SWORD || stack.getItem() == Items.DIAMOND_SWORD;
    }

    public static boolean isSwordOrMace(ItemStack stack) {
        return isSword(stack) || isMace(stack);
    }
}
