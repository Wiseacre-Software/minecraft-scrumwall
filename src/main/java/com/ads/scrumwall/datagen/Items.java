package com.ads.scrumwall.datagen;

import com.ads.scrumwall.ScrumWall;
import com.ads.scrumwall.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ScrumWall.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(Registration.EPICITEM.get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(ScrumWall.MODID, "item/epicitem"));
        withExistingParent(Registration.EPICBLOCK_ITEM.get().getRegistryName().getPath(), new ResourceLocation(ScrumWall.MODID, "block/epicblock"));
    }
}
