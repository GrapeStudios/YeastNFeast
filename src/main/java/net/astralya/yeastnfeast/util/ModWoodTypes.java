package net.astralya.yeastnfeast.util;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class ModWoodTypes {
    public static final BlockSetType MAPLE_SET = BlockSetTypeBuilder.copyOf(BlockSetType.BIRCH).register(Identifier.of(YeastNFeastMod.MODID, "maple"));
    public static final WoodType MAPLE_WOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.BIRCH).register(Identifier.of(YeastNFeastMod.MODID,"maple"), MAPLE_SET);
}
