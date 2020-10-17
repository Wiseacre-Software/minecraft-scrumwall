package com.adsminecraft.scrumwall.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BacklogBlock extends Block {

    public BacklogBlock() {
        super(Properties.create(Material.WOOL)
                .sound(SoundType.CLOTH)
                .hardnessAndResistance(0.8f));
    }
}
