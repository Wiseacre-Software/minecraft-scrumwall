package com.adsminecraft.scrumwall.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.adsminecraft.scrumwall.init.Registration.BACKLOG_TILE;

public class BacklogTile extends TileEntity {

    private ItemStackHandler itemHandler = createHandler();
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private int counter;

    public BacklogTile() {
        super(BACKLOG_TILE.get());
    }

    @Override
    public void remove() {
        super.remove();
        handler.invalidate();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        counter = nbt.getInt("counter");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        nbt.putInt("counter", counter);
        return super.write(nbt);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() == Items.DIAMOND;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stack.getItem() != Items.DIAMOND) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
}
