package net.grapes.yeastnfeast.util;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType MAPLE = WoodType.register(new WoodType(YeastNFeastMod.MOD_ID+ ":maple", BlockSetType.OAK));
}
