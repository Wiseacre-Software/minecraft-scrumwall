package com.adsminecraft.scrumwall.items;

import com.adsminecraft.scrumwall.ScrumWall;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(ScrumWall.TAB));
    }
}
