package com.salad.overlord.blocks.tile_entities;

import com.salad.overlord.blocks.ModBlocks;
import com.salad.overlord.blocks.containers.UpkeepBoxContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UpkeepBoxTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);


    public UpkeepBoxTile() {
        super(ModBlocks.UPKEEP_BOX_TILE);
    }

    @Override
    public void tick() {
    }

    //Next 2 functions are to make sure the inventory is saved and loaded
    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });
        return super.write(tag);
    }

    private ItemStackHandler createHandler() {
       return new ItemStackHandler(1) {
           @Override
           public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
               return stack.getItem() == Items.COAL; //TEMP
           }

           @Nonnull
           @Override
           public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
               if(stack.getItem() != Items.COAL) {
                   return stack;
               }
               return super.insertItem(slot, stack, simulate);
           }
       };
    }

    //Ways to add things to tile entities
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new UpkeepBoxContainer(i, world, pos, playerInventory, playerEntity);
    }
}
