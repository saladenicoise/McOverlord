package com.salad.overlord;

import com.salad.overlord.blocks.GenericBlocks;
import com.salad.overlord.blocks.ModBlocks;
import com.salad.overlord.blocks.containers.ForgeStationContainer;
import com.salad.overlord.blocks.containers.UpkeepBoxContainer;
import com.salad.overlord.blocks.stations.ForgeStation;
import com.salad.overlord.blocks.stations.UpkeepBox;
import com.salad.overlord.blocks.tile_entities.ForgeStationTile;
import com.salad.overlord.blocks.tile_entities.UpkeepBoxTile;
import com.salad.overlord.items.GenericItems;
import com.salad.overlord.setup.ClientProxy;
import com.salad.overlord.setup.IProxy;
import com.salad.overlord.setup.ModSetup;
import com.salad.overlord.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*TODO:
Important:
See about libraries(AutoReg, Bookshelf)

Block Ideas
ISSUES:
Forging Station UI instantly closes
Forging Station:
-Necessary for crafting the higher end tools(mithril+)
-Necessary for infusing items with prismatic ores(each one will give a special bonus), will require a catalyst

Items
Mithril Armor, in Forge Station, Diamonds with 1/2 Mithril
Orichalcum Armor, in Forge Station, Mithril with 1/2 Orichalcum
Adamantite Armor, in Forge Station, Orichalcum with 1/2 Adamantite

 */

@Mod(Overlord.MOD_ID)
public class Overlord {
    public static final String MOD_ID = "overlord";
    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);//Print onto console
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static ModSetup setup = new ModSetup();

    public Overlord() {
        //Register the setup function
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> blockRegisterEvent) {
            blockRegisterEvent.getRegistry().register(new GenericBlocks.CopperBlock());
            blockRegisterEvent.getRegistry().register(new GenericBlocks.SilverBlock());
            blockRegisterEvent.getRegistry().register(new GenericBlocks.PlatinumBlock());
            blockRegisterEvent.getRegistry().register(new ForgeStation());
            blockRegisterEvent.getRegistry().register(new UpkeepBox());
            LOGGER.info("Registered Blocks");
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> itemRegisterEvent) {
            Item.Properties properties = new Item.Properties().group(setup.overlordMaterialsItemGroup);
            itemRegisterEvent.getRegistry().register(new BlockItem(ModBlocks.COPPER_BLOCK, properties).setRegistryName("copper_block"));
            itemRegisterEvent.getRegistry().register(new BlockItem(ModBlocks.SILVER_BLOCK, properties).setRegistryName("silver_block"));
            itemRegisterEvent.getRegistry().register(new BlockItem(ModBlocks.PLATINUM_BLOCK, properties).setRegistryName("platinum_block"));
            itemRegisterEvent.getRegistry().register(new BlockItem(ModBlocks.UPKEEP_BOX, properties).setRegistryName("upkeep_box"));
            itemRegisterEvent.getRegistry().register(new BlockItem(ModBlocks.FORGE_STATION, properties).setRegistryName("forge_station"));
            itemRegisterEvent.getRegistry().register(new GenericItems.CopperIngot());
            itemRegisterEvent.getRegistry().register(new GenericItems.SilverIngot());
            itemRegisterEvent.getRegistry().register(new GenericItems.PlatinumIngot());
            itemRegisterEvent.getRegistry().register(new GenericItems.MithrilIngot());
            itemRegisterEvent.getRegistry().register(new GenericItems.OrichalcumIngot());
            itemRegisterEvent.getRegistry().register(new GenericItems.AdamantiteIngot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic1Ingot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic2Ingot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic3Ingot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic4Ingot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic5Ingot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic6Ingot());
            itemRegisterEvent.getRegistry().register(new GenericItems.Prismatic7Ingot());
            LOGGER.info("Registered Items");
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(UpkeepBoxTile::new, ModBlocks.UPKEEP_BOX).build(null).setRegistryName("upkeep_box"));
            event.getRegistry().register(TileEntityType.Builder.create(ForgeStationTile::new, ModBlocks.FORGE_STATION).build(null).setRegistryName("forge_station"));
            LOGGER.info("Tile Entities Registered");
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos position = data.readBlockPos();
                return new UpkeepBoxContainer(windowId, proxy.getClientWorld(), position, inv, proxy.getClientPlayer());
            }).setRegistryName("upkeep_box"));
            event.getRegistry().register(ForgeStationContainer.TYPE);
        }
    }
 }
