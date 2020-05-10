package com.salad.overlord.setup;

import com.salad.overlord.blocks.ModBlocks;
import com.salad.overlord.items.GenericItems;
import com.salad.overlord.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    public ItemGroup overlordMaterialsItemGroup = new ItemGroup("overlordMaterialsItemGroup") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.PRISMATIC_1_INGOT);
        }
    };
    public ItemGroup overlordMartialItemGroup = new ItemGroup("overlordMartialItemGroup") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.PRISMATIC_2_INGOT);
        }
    };
    public ItemGroup overlordMagicItemGroup = new ItemGroup("overlordMagicItemGroup") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.PRISMATIC_3_INGOT);
        }
    };

    public void init() {

    }
}
