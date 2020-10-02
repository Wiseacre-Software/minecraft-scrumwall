package com.ads.scrumwall.datagen;

import com.ads.scrumwall.ScrumWall;
import com.ads.scrumwall.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ScrumWall.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerEpicBlock();
    }

    private void registerEpicBlock() {
        ResourceLocation txt = new ResourceLocation(ScrumWall.MODID, "block/epicblock");
        BlockModelBuilder modelEpicblock = models().cube("epicblock", txt, txt, new ResourceLocation(ScrumWall.MODID, "block/epicblock_front"), txt, txt, txt);
        BlockModelBuilder modelEpicblockPowered = models().cube("epicblock_powered", txt, txt, new ResourceLocation(ScrumWall.MODID, "block/epicblock_powered"), txt, txt, txt);
        orientedBlock(Registration.EPICBLOCK.get(), state -> {
            if (state.get(BlockStateProperties.POWERED)) {
                return modelEpicblockPowered;
            } else {
                return modelEpicblock;
            }
        });
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.get(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getOffset() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.getHorizontalIndex() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}
