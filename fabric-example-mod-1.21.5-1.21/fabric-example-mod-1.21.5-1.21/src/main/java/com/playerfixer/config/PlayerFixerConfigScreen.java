package com.playerfixer.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayerFixerConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("PlayerFixer Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General Settings"));

        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.of("Auto Aim"), PlayerFixerConfig.autoAim)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> PlayerFixerConfig.autoAim = newValue)
                .build());

        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.of("Auto Switch Mace"), PlayerFixerConfig.autoSwitch)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> PlayerFixerConfig.autoSwitch = newValue)
                .build());

        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.of("Auto Attack"), PlayerFixerConfig.autoAttack)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> PlayerFixerConfig.autoAttack = newValue)
                .build());

        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.of("Stealth Mode"), PlayerFixerConfig.stealthMode)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> PlayerFixerConfig.stealthMode = newValue)
                .build());

        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.of("Shield Breaker"), PlayerFixerConfig.shieldBreaker)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> PlayerFixerConfig.shieldBreaker = newValue)
                .build());

        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.of("Mace After Shield Break"), PlayerFixerConfig.maceAfterShield)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> PlayerFixerConfig.maceAfterShield = newValue)
                .build());

        general.addEntry(builder.entryBuilder()
                .startFloatField(Text.of("Aim Speed (0.0–1.0)"), PlayerFixerConfig.aimSpeed)
                .setMin(0.0f)
                .setMax(1.0f)
                .setDefaultValue(0.6f)
                .setTooltip(Text.of("How fast the auto-aim adjusts (1.0 = instant, 0.1 = slow tracking)"))
                .setSaveConsumer(newValue -> PlayerFixerConfig.aimSpeed = newValue)
                .build());
        general.addEntry(builder.entryBuilder()
    .startBooleanToggle(Text.of("Auto Shield Block"), PlayerFixerConfig.autoShield)
    .setDefaultValue(true)
    .setSaveConsumer(newValue -> PlayerFixerConfig.autoShield = newValue)
    .build());


        builder.setSavingRunnable(() -> {
            // Save logic here if you're persisting settings
        });

        return builder.build();
    }
}
