package com.adsminecraft.scrumwall;

import com.adsminecraft.scrumwall.init.ClientInit;
import com.adsminecraft.scrumwall.init.Config;
import com.adsminecraft.scrumwall.init.ModInit;
import com.adsminecraft.scrumwall.init.Registration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ScrumWall.MODID)
public class ScrumWall {
    public static final String MODID = "scrumwall";

    private static final Logger LOGGER = LogManager.getLogger();

    public ScrumWall() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModInit::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientInit::init);

        Registration.init();
    }
}
