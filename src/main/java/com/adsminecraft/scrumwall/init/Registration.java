package com.adsminecraft.scrumwall.init;

import com.adsminecraft.scrumwall.block.BacklogBlock;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.adsminecraft.scrumwall.ScrumWall.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<BacklogBlock> BACKLOGBLOCK = BLOCKS.register("backlogblock", BacklogBlock::new);
    public static final RegistryObject<Item> BACKLOG_ITEM = ITEMS.register("backlogblock", () -> new BlockItem(BACKLOGBLOCK.get(), new Item.Properties().group(ModInit.ITEM_GROUP)));
//    public static final RegistryObject<TileEntityType<BacklogTile>> BACKLOG_TILE = TILES.register("backlogblock", () -> TileEntityType.Builder.create(BacklogTile::new, BACKLOGBLOCK.get()).build(null));
}
