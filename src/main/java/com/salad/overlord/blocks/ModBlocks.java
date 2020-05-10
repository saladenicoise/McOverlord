package com.salad.overlord.blocks;

import com.salad.overlord.blocks.containers.ForgeStationContainer;
import com.salad.overlord.blocks.containers.UpkeepBoxContainer;
import com.salad.overlord.blocks.stations.ForgeStation;
import com.salad.overlord.blocks.stations.UpkeepBox;
import com.salad.overlord.blocks.tile_entities.ForgeStationTile;
import com.salad.overlord.blocks.tile_entities.UpkeepBoxTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
    @ObjectHolder("overlord:copper_block")
    public static GenericBlocks.CopperBlock COPPER_BLOCK;
    @ObjectHolder("overlord:silver_block")
    public static GenericBlocks.SilverBlock SILVER_BLOCK;
    @ObjectHolder("overlord:platinum_block")
    public static GenericBlocks.PlatinumBlock PLATINUM_BLOCK;
    @ObjectHolder("overlord:forge_station")
    public static ForgeStation FORGE_STATION;
    @ObjectHolder("overlord:forge_station")
    public static TileEntityType<ForgeStationTile> FORGE_STATION_TILE;
    @ObjectHolder("overlord:forge_station")
    public static ContainerType<ForgeStationContainer> FORGE_STATION_CONTAINER;
    @ObjectHolder("overlord:upkeep_box")
    public static UpkeepBox UPKEEP_BOX;
    @ObjectHolder("overlord:upkeep_box")
    public static TileEntityType<UpkeepBoxTile> UPKEEP_BOX_TILE;
    @ObjectHolder("overlord:upkeep_box")
    public static ContainerType<UpkeepBoxContainer> UPKEEP_BOX_CONTAINER;
}
