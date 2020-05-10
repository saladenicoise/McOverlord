package com.salad.overlord.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GenericBlocks {

    public static class CopperBlock extends Block {
        public CopperBlock() {
            super(Properties
                    .create(Material.IRON)
                    .sound(SoundType.METAL)
                    .hardnessAndResistance(2.0f)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2)
            );
            setRegistryName("copper_block");
        }
    }

    public static class SilverBlock extends Block {
        public SilverBlock() {
            super(Properties
                    .create(Material.IRON)
                    .sound(SoundType.METAL)
                    .hardnessAndResistance(2.0f)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2)
            );
            setRegistryName("silver_block");
        }
    }

    public static class PlatinumBlock extends Block {
        public PlatinumBlock() {
            super(Properties
                    .create(Material.IRON)
                    .sound(SoundType.METAL)
                    .hardnessAndResistance(2.0f)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2)
            );
            setRegistryName("platinum_block");
        }
    }
}
