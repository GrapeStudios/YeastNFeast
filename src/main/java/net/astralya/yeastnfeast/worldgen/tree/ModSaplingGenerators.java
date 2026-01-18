package net.astralya.yeastnfeast.worldgen.tree;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.worldgen.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator MAPLE = new SaplingGenerator(YeastNFeastMod.MODID + ":maple",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MAPLE_KEY), Optional.empty());
    public static final SaplingGenerator HAWTHORN = new SaplingGenerator(YeastNFeastMod.MODID + ":hawthorn",
            Optional.empty(), Optional.of(ModConfiguredFeatures.HAWTHORN_TREE_KEY), Optional.empty());
    public static final SaplingGenerator LEMON = new SaplingGenerator(YeastNFeastMod.MODID + ":lemon",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LEMON_TREE_KEY), Optional.empty());
}
