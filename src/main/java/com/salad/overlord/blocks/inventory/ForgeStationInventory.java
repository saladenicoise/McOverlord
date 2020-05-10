package com.salad.overlord.blocks.inventory;

import com.salad.overlord.blocks.tile_entities.ForgeStationTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

public class ForgeStationInventory extends CraftingInventory {
    private final ForgeStationTile tile;
    private final Container container;

    public ForgeStationInventory(final Container eventHandlerIn, final ForgeStationTile tileEntity) {
        super(eventHandlerIn, 3, 3);
        tile = tileEntity;
        container = eventHandlerIn;
    }

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(final int index) {
        return tile.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(final int index, final int count) {
        final ItemStack stack = tile.decrStackSize(index, count);
        if (stack != ItemStack.EMPTY) {
            tile.updateInvs();
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(final int index, final ItemStack stack) {
        tile.setInventorySlotContents(index, stack);
        tile.updateInvs();
    }

    @Override
    public void markDirty() {
        tile.markDirty();
        final BlockState state = tile.getWorld().getBlockState(tile.getPos());
        tile.getWorld().notifyBlockUpdate(tile.getPos(), state, state, 3);
    }

    @Override
    public void openInventory(final PlayerEntity player) {
        super.openInventory(player);
        tile.onOpen(this);
    }

    @Override
    public void closeInventory(final PlayerEntity player) {
        super.closeInventory(player);
        tile.onClose(this);
    }

    public void changed() {
        container.onCraftMatrixChanged(this);
    }
}
