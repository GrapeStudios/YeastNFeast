package net.astralya.yeastnfeast.item;

import net.astralya.yeastnfeast.util.ModUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup YEASTNFEAST = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(YeastNFeastMod.MODID, "yeastnfeast"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.yeastnfeast"))
                    .icon(() -> new ItemStack(ModItems.TANKARD))
                    .entries((displayContext, entries) -> {
                        // Functional Blocks
                        entries.add(ModItems.KEG);
                        entries.add(ModBlocks.TREE_TAP);

                        // Resources
                        entries.add(ModItems.YEAST);
                        entries.add(ModItems.MAPLE_SYRUP);
                        entries.add(ModItems.MOLASSES);
                        entries.add(ModItems.MILK_BOTTLE);

                        // Seeds
                        entries.add(ModItems.BARLEY_SEEDS);
                        entries.add(ModItems.RYE_SEEDS);
                        entries.add(ModItems.MINT_SEEDS);

                        // Food Resources
                        entries.add(ModItems.ELDERBERRIES);
                        entries.add(ModItems.GARLIC);
                        entries.add(ModItems.GINGER);
                        entries.add(ModItems.HAWTHORN_BERRIES);
                        entries.add(ModItems.LEMON);
                        entries.add(ModItems.MINT);
                        entries.add(ModItems.ROSE_HIPS);
                        entries.add(ModItems.BARLEY);
                        entries.add(ModItems.RYE);

                        // Storage Bags
                        entries.add(ModBlocks.BAG_OF_ELDERBERRIES);
                        entries.add(ModBlocks.BAG_OF_GARLIC);
                        entries.add(ModBlocks.BAG_OF_GINGER);
                        entries.add(ModBlocks.BAG_OF_HAWTHORN_BERRIES);
                        entries.add(ModBlocks.BAG_OF_LEMON);
                        entries.add(ModBlocks.BAG_OF_MINT);
                        entries.add(ModBlocks.BAG_OF_ROSE_HIPS);
                        entries.add(ModBlocks.BARLEY_BLOCK);
                        entries.add(ModBlocks.RYE_BLOCK);

                        // Baked
                        entries.add(ModItems.BARLEY_BREAD);
                        entries.add(ModItems.RYE_BREAD);
                        entries.add(ModItems.MOLASSES_BREAD);
                        entries.add(ModItems.BERRY_ROLL);
                        entries.add(ModItems.ROSE_TART);
                        entries.add(ModItems.ELDERBERRY_PIE);
                        entries.add(ModItems.APPLE_PIE);

                        // Food
                        entries.add(ModItems.SWEET_PORRIDGE);
                        entries.add(ModItems.SPICED_PORRIDGE);
                        entries.add(ModItems.BARLEY_AND_BEEF_STEW);
                        entries.add(ModItems.SALMON_CHOWDER);
                        entries.add(ModItems.MEAD_BRAISED_PORK);
                        entries.add(ModItems.HERBAL_COD);
                        entries.add(ModItems.LEMON_GLAZED_CHICKEN);
                        entries.add(ModItems.FORAGER_FEAST);
                        entries.add(ModItems.MAPLE_GLAZED_RABBIT);

                        // Mead
                        entries.add(ModItems.TANKARD);
                        entries.add(ModItems.HONEY_MEAD);
                        entries.add(ModItems.MOLASSES_MEAD);
                        entries.add(ModItems.SOUR_MEAD);
                        entries.add(ModItems.THORNBERRY_MEAD);
                        entries.add(ModItems.BLOSSOM_MEAD);
                        entries.add(ModItems.AMBER_MEAD);

                        // Jams
                        entries.add(ModItems.JAR);
                        entries.add(ModItems.APPLE_JAM);
                        entries.add(ModItems.CHORUS_FRUIT_JAM);
                        entries.add(ModItems.ELDERBERRIES_JAM);
                        entries.add(ModItems.GLOW_BERRIES_JAM);
                        entries.add(ModItems.GOLDEN_APPLE_JAM);
                        entries.add(ModItems.HAWTHORN_BERRIES_JAM);
                        entries.add(ModItems.LEMON_JAM);
                        entries.add(ModItems.MELON_JAM);
                        entries.add(ModItems.ROSE_HIPS_JAM);
                        entries.add(ModItems.SWEET_BERRIES_JAM);

                        // Wood-Related Items
                        entries.add(ModBlocks.MAPLE_LEAVES);
                        entries.add(ModBlocks.MAPLE_LOG);
                        entries.add(ModBlocks.MAPLE_WOOD);
                        entries.add(ModBlocks.STRIPPED_MAPLE_LOG);
                        entries.add(ModBlocks.STRIPPED_MAPLE_WOOD);
                        entries.add(ModBlocks.MAPLE_PLANKS);
                        entries.add(ModBlocks.MAPLE_STAIRS);
                        entries.add(ModBlocks.MAPLE_SLAB);
                        entries.add(ModBlocks.MAPLE_FENCE);
                        entries.add(ModBlocks.MAPLE_FENCE_GATE);
                        entries.add(ModBlocks.MAPLE_DOOR);
                        entries.add(ModBlocks.MAPLE_TRAPDOOR);
                        entries.add(ModBlocks.MAPLE_PRESSURE_PLATE);
                        entries.add(ModBlocks.MAPLE_BUTTON);
                        entries.add(ModItems.MAPLE_BOAT);
                        entries.add(ModItems.MAPLE_CHEST_BOAT);
                        entries.add(ModItems.MAPLE_SIGN);
                        entries.add(ModItems.MAPLE_HANGING_SIGN);

                        entries.add(ModBlocks.MAPLE_SAPLING);
                        entries.add(ModBlocks.LEMON_SAPLING);
                        entries.add(ModBlocks.HAWTHORN_SAPLING);

                        // Compat Items
                        if (ModUtils.isModLoaded("patchouli")) {
                            entries.add(ModItems.HOMESTEADERS_HANDBOOK);
                        }

                    }).build());

    public static void registerItemGroups() {
        YeastNFeastMod.LOGGER.info("Registering Item Group for " + YeastNFeastMod.MODID);
    }
}
