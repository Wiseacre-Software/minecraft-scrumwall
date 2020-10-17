package com.adsminecraft.scrumwall.init;

import com.adsminecraft.scrumwall.ScrumWall;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ScrumWall.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInit {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("mytutorial") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.BACKLOGBLOCK.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {

    }

}
