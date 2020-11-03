package com.adsminecraft.scrumwall.block;

import com.adsminecraft.scrumwall.init.Registration;
import com.adsminecraft.scrumwall.util.CraftingSlotItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import static com.adsminecraft.scrumwall.ScrumWall.LOGGER;

public class BacklogContainer extends Container {

    private BacklogTile tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public BacklogContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Registration.BACKLOG_CONTAINER.get(), windowId);
        LOGGER.info("BacklogContainer - entering");
        tileEntity = (BacklogTile) world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (tileEntity == null) { return; }

        // input slot
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, BacklogTile.ItemHandlers.INPUT).ifPresent(h -> {
            this.addSlot(new SlotItemHandler(h, 0, 10, 7));
        });

        // grid
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, BacklogTile.ItemHandlers.GRID).ifPresent(h -> {
            int index = 0;
            for (int rowPos = 0; rowPos < 3; rowPos++) {
                for (int colPos = 0; colPos < 3; colPos++) {
                    this.addSlot(new CraftingSlotItemHandler(h, index,
                            46 + colPos * 18,
                            7 + rowPos * 18));
                    index++;
                }
            }
        });

        // player inventory
        layoutPlayerInventorySlots(10, 70);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(),
                tileEntity.getPos()), playerEntity, Registration.BACKLOG_BLOCK.get());

    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        LOGGER.info("transferStackInSlot - entering");
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
                if (stack.getItem() == Items.DIAMOND) {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
