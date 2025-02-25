package net.grapes.yeastnfeast.util;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class ModWoodTypes {
    public static final WoodType MAPLE = WoodTypeRegistry.register(new Identifier(YeastNFeastMod.MOD_ID, "maple"), BlockSetType.OAK);
}
