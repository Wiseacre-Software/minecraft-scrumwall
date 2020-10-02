package com.ads.scrumwall.setup;

import com.electronwill.nightconfig.core.ConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_EPICBLOCK = "epicblock";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue EPICBLOCK_MAXPOWER;
    public static ForgeConfigSpec.IntValue EPICBLOCK_GENERATE;
    public static ForgeConfigSpec.IntValue EPICBLOCK_SEND;
    public static ForgeConfigSpec.IntValue EPICBLOCK_TICKS;

    static {

        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        CLIENT_BUILDER.pop();

        SERVER_BUILDER.comment("Power settings").push(CATEGORY_POWER);

        setupEpicBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_BUILDER.pop();


        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupEpicBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("EpicBlock settings").push(SUBCATEGORY_EPICBLOCK);

        EPICBLOCK_MAXPOWER = SERVER_BUILDER.comment("Maximum power for the FirstBlock generator")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        EPICBLOCK_GENERATE = SERVER_BUILDER.comment("Power generation per diamond")
                .defineInRange("generate", 1000, 0, Integer.MAX_VALUE);
        EPICBLOCK_SEND = SERVER_BUILDER.comment("Power generation to send per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);
        EPICBLOCK_TICKS = SERVER_BUILDER.comment("Ticks per diamond")
                .defineInRange("ticks", 20, 0, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
}
