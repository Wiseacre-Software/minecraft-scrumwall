package com.adsminecraft.scrumwall.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CraftingSlotItemHandler extends SlotItemHandler {

    @Override
    public boolean isItemValid(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void putStack(ItemStack stack) {
        super.putStack(stack.copy());
        stack.grow(1);//hack for JEI when they auto-fill recipes, re fill it after it drains so its a 'mock slot'
    }

    public CraftingSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
}
