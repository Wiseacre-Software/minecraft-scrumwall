package com.adsminecraft.scrumwall.init;

import com.adsminecraft.scrumwall.block.BacklogBlock;
import com.adsminecraft.scrumwall.block.BacklogContainer;
import com.adsminecraft.scrumwall.block.BacklogTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
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

    public static final RegistryObject<BacklogBlock> BACKLOG_BLOCK = BLOCKS.register("backlog_block", BacklogBlock::new);
    public static final RegistryObject<Item> BACKLOG_ITEM = ITEMS.register("backlog_block",
            () -> new BlockItem(BACKLOG_BLOCK.get(), new Item.Properties().group(ModInit.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<BacklogTile>> BACKLOG_TILE = TILES.register("backlog_block",
            () -> TileEntityType.Builder.create(BacklogTile::new, BACKLOG_BLOCK.get()).build(null));
    public static final RegistryObject<ContainerType<BacklogContainer>> BACKLOG_CONTAINER = CONTAINERS.register("backlog",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new BacklogContainer(windowId, world, pos, inv, inv.player);
            }));
}
