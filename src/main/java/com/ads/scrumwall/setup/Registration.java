package com.ads.scrumwall.setup;

import com.ads.scrumwall.blocks.EpicBlock;
import com.ads.scrumwall.blocks.EpicBlockContainer;
import com.ads.scrumwall.blocks.EpicBlockTile;
import com.ads.scrumwall.items.EpicItem;
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

import static com.ads.scrumwall.ScrumWall.MODID;

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

    public static final RegistryObject<EpicBlock> EPICBLOCK = BLOCKS.register("epicblock", EpicBlock::new);
    public static final RegistryObject<Item> EPICBLOCK_ITEM = ITEMS.register("epicblock", () -> new BlockItem(EPICBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<EpicBlockTile>> EPICBLOCK_TILE = TILES.register("epicblock", () -> TileEntityType.Builder.create(EpicBlockTile::new, EPICBLOCK.get()).build(null));

    public static final RegistryObject<ContainerType<EpicBlockContainer>> EPICBLOCK_CONTAINER = CONTAINERS.register("epicblock", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new EpicBlockContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<EpicItem> EPICITEM = ITEMS.register("epicitem", EpicItem::new);
}
