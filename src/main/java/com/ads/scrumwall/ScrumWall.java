package com.ads.scrumwall;

import com.ads.scrumwall.setup.Config;
import com.ads.scrumwall.setup.ModSetup;
import com.ads.scrumwall.setup.Registration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ScrumWall.MODID)
public class ScrumWall {

    public static final String MODID = "scrumwall";

    private static final Logger LOGGER = LogManager.getLogger();

    public ScrumWall() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Registration.init();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }

}
