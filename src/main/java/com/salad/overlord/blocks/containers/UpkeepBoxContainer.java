package com.salad.overlord.blocks.containers;

import com.salad.overlord.blocks.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import static com.salad.overlord.blocks.ModBlocks.UPKEEP_BOX_CONTAINER;

public class UpkeepBoxContainer extends Container {

    private final TileEntity tileEntity;
    private final PlayerEntity playerEntity;
    private final IItemHandler playerInventory;

    public UpkeepBoxContainer(int id, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        super(UPKEEP_BOX_CONTAINER, id);
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = playerEntity;
        this.playerInventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 64, 24));
        });
        layoutPlayerInventorySlots(8,84);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.UPKEEP_BOX);
    }

    //Creates amount of slots, starting at x and y with dx interval
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for(int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    //Generetates a box of slots
    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for(int i = 0; i < verAmount; i++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        //Player Inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        //Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
