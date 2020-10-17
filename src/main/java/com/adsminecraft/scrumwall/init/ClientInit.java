package com.adsminecraft.scrumwall.init;

import com.adsminecraft.scrumwall.ScrumWall;
import com.adsminecraft.scrumwall.block.BacklogScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ScrumWall.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientInit {
    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registration.BACKLOG_CONTAINER.get(), BacklogScreen::new);
    }
}
