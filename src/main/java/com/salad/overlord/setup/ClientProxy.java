package com.salad.overlord.setup;

import com.salad.overlord.blocks.ModBlocks;
import com.salad.overlord.blocks.screens.ForgeStationScreen;
import com.salad.overlord.blocks.screens.UpkeepBoxScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy{
    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.UPKEEP_BOX_CONTAINER, UpkeepBoxScreen::new);
        ScreenManager.registerFactory(ModBlocks.FORGE_STATION_CONTAINER, ForgeStationScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
