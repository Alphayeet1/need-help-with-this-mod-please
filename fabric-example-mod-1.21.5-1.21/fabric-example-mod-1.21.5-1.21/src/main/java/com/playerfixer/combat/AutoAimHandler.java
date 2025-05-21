package com.playerfixer.combat;

import com.playerfixer.config.PlayerFixerConfig;
import com.playerfixer.util.WeaponUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class AutoAimHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> { ... });
            if (PlayerFixerConfig.globalPause || !PlayerFixerConfig.autoAim) return;
            if (!client.options.attackKey.isPressed()) return;

            if (client.player == null || client.currentScreen != null) return;

            if (!WeaponUtils.isSwordOrMace(client.player.getMainHandStack())) return;

            Entity target = client.targetedEntity;
            if (!(target instanceof LivingEntity)) return;

            // Skip if it's on no-attack list
            String name = target.getName().getString();
            if (PlayerFixerConfig.noAttackPlayers.contains(name)) return;

            // Skip auto-aim if Breach Mace is in hand
            if (WeaponUtils.isBreachMace(client.player.getMainHandStack())) return;

            Vec3d targetPos = target.getPos().add(0, target.getHeight() / 2.0, 0);
            Vec3d eyePos = client.player.getCameraPosVec(1.0F);

            Vec3d delta = targetPos.subtract(eyePos);
            double distXZ = Math.sqrt(delta.x * delta.x + delta.z * delta.z);

            float targetYaw = (float)(Math.toDegrees(Math.atan2(delta.z, delta.x)) - 90.0F);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(delta.y, distXZ)));

            float currentYaw = client.player.getYaw();
            float currentPitch = client.player.getPitch();

            float speed = PlayerFixerConfig.aimSpeed;
            float newYaw = lerpAngle(speed, currentYaw, targetYaw);
            float newPitch = lerpAngle(speed, currentPitch, targetPitch);

            client.player.setYaw(newYaw);
            client.player.setPitch(newPitch);
            private static float lerpAngle(float factor, float start, float end) {
                float delta = wrapDegrees(end - start);
                return start + delta * factor;
            }

            private static float wrapDegrees(float degrees) {
                degrees = degrees % 360.0F;
                if (degrees >= 180.0F) degrees -= 360.0F;
                if (degrees < -180.0F) degrees += 360.0F;
                return degrees;
            }

        });
    }
}
