package com.adsminecraft.scrumwall.jei;

import com.adsminecraft.scrumwall.ScrumWall;
import com.adsminecraft.scrumwall.block.BacklogBlock;
import com.adsminecraft.scrumwall.block.BacklogContainer;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class ScrumWallPlugin implements IModPlugin {

    private ResourceLocation UID = new ResourceLocation(ScrumWall.MODID, "default");

    @Override
    public ResourceLocation getPluginUid() { return UID; }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
        registry.addRecipeTransferHandler(new ScrumWallRecipeTransferHandler(registry.getTransferHelper()), VanillaRecipeCategoryUid.CRAFTING);
//        registry.addRecipeTransferHandler(BacklogContainer.class, VanillaRecipeCategoryUid.CRAFTING,
//                1, 9, //recipeSLotStart, recipeSlotCount
//                10, 4 * 9);// inventorySlotStart, inventorySlotCount
    }
}
