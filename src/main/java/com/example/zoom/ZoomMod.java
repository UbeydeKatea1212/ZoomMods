package com.example.zoom;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod("zoom")
public class ZoomMod {
    private static final UUID REACH_UUID = UUID.fromString("7a4b3c2d-1e0f-9a8b-7c6d-5e4f3a2b1c0d");
    private static final double TARGET_REACH = 6.0;

    public ZoomMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        AttributeInstance reach = player.getAttribute(ForgeMod.REACH_DISTANCE.get());

        if (reach != null && reach.getModifier(REACH_UUID) == null) {
            double requiredAddition = TARGET_REACH - reach.getBaseValue();
            AttributeModifier modifier = new AttributeModifier(
                REACH_UUID,
                "Zoom Reach Modifier",
                requiredAddition,
                AttributeModifier.Operation.ADDITION
            );
            reach.addPermanentModifier(modifier);
        }
    }
}
