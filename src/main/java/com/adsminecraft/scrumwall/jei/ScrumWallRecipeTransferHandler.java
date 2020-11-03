package com.adsminecraft.scrumwall.jei;

import com.adsminecraft.scrumwall.block.BacklogContainer;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.IGuiIngredient;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;


public class ScrumWallRecipeTransferHandler implements IRecipeTransferHandler<BacklogContainer> {

    private IRecipeTransferHandlerHelper recipeTransferHelper;

    public ScrumWallRecipeTransferHandler(IRecipeTransferHandlerHelper helper) {
        this.recipeTransferHelper = helper;
    }

    @Override
    public Class<BacklogContainer> getContainerClass() {
        return BacklogContainer.class;
    }

    @Nullable
    @Override
    public IRecipeTransferError transferRecipe(BacklogContainer container, IRecipeLayout recipeLayout, PlayerEntity player, boolean maxTransfer, boolean doTransfer) {
        Map<Integer, ? extends IGuiIngredient<ItemStack>> ingredients = recipeLayout.getItemStacks().getGuiIngredients();
        CompoundNBT tag = new CompoundNBT();
        ListNBT normalIngredients = new ListNBT();
        ListNBT crystalIngredients = new ListNBT();
        ingredients.forEach((i, o) -> {
            if (i == 0)
                return;
            ListNBT subTypes = new ListNBT();
            ItemStack displayed = o.getDisplayedIngredient();
            if (displayed != null && !displayed.isEmpty()) // Try using the displayed one first
                subTypes.add(o.getDisplayedIngredient().serializeNBT());
            o.getAllIngredients().forEach(stack -> {
                if (!ItemStack.areItemStacksEqual(stack, displayed)) // No point sending the same stack twice
                    subTypes.add(stack.serializeNBT());
            });
            if (o.isInput())
                normalIngredients.add(subTypes);
            else
                crystalIngredients.add(subTypes);
        });
        tag.put("normal", normalIngredients);
        tag.put("crystal", crystalIngredients);
        // TODO: Actually check and report errors
//        if (doTransfer)
//            PacketHandler.sendToServer(new PacketJEIRecipe(tag));
        return null;
    }
}
