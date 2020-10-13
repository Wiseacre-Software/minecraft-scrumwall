package com.adsminecraft.scrumwall.init;

import com.adsminecraft.scrumwall.ScrumWall;
import com.adsminecraft.scrumwall.blocks.Oven;
import com.adsminecraft.scrumwall.blocks.RubyBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ScrumWall.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> OVEN = BLOCKS.register("oven", Oven::new);

}
