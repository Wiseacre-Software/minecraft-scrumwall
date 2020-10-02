package com.ads.scrumwall.datagen;

import com.ads.scrumwall.setup.Registration;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.EPICBLOCK.get(), createStandardTable("epicblock", Registration.EPICBLOCK.get()));
    }
}
