package com.example.jumponhit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class JumpOnHitClient implements ClientModInitializer {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private int lastHurtTime = 0;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            if (mc.player != null) {
                if (mc.player.hurtTime > 0 && mc.player.hurtTime != lastHurtTime) {
                    mc.player.jump(); // force jump locally when damaged
                }
                lastHurtTime = mc.player.hurtTime;
            }
        });
    }
}