package net.grapes.yeastnfeast.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.entity.ModBoats;
import net.grapes.yeastnfeast.item.custom.JamItem;
import net.grapes.yeastnfeast.item.custom.MilkBottleItem;
import net.grapes.yeastnfeast.item.custom.SweetenerBottleItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    // Resources
    public static final Item YEAST = registerItem("yeast",
            new Item(new FabricItemSettings()));
    public static final Item MAPLE_SYRUP = registerItem("maple_syrup",
            new SweetenerBottleItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.MAPLE_SYRUP)));
    public static final Item MOLASSES = registerItem("molasses",
            new SweetenerBottleItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.MOLASSES)));
    public static final Item MILK_BOTTLE = registerItem("milk_bottle",
            new MilkBottleItem(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));

    // Seeds
    public static final Item BARLEY_SEEDS = registerItem("barley_seeds",
            new AliasedBlockItem(ModBlocks.BARLEY_CROP, new FabricItemSettings()));
    public static final Item RYE_SEEDS = registerItem("rye_seeds",
            new AliasedBlockItem(ModBlocks.RYE_CROP, new FabricItemSettings()));
    public static final Item MINT_SEEDS = registerItem("mint_seeds",
            new AliasedBlockItem(ModBlocks.MINT_CROP, new FabricItemSettings()));

    // Fruits & Berries
    public static final Item ELDERBERRIES = registerItem("elderberries",
            new BlockItem(ModBlocks.ELDERBERRY_BUSH,
                    new FabricItemSettings().food(ModFoodComponents.ELDERBERRIES)));
    public static final Item HAWTHORN_BERRIES = registerItem("hawthorn_berries",
            new Item(new FabricItemSettings().food(ModFoodComponents.HAWTHORN_BERRIES)));
    public static final Item ROSE_HIPS = registerItem("rose_hips",
            new BlockItem(ModBlocks.ROSE_HIPS_BUSH,
                    new FabricItemSettings().food(ModFoodComponents.ROSE_HIPS)));
    public static final Item LEMON = registerItem("lemon",
            new Item(new FabricItemSettings().food(ModFoodComponents.LEMON)));

    // Herbs & Spices
    public static final Item MINT = registerItem("mint",
            new Item(new FabricItemSettings()));
    public static final Item GINGER = registerItem("ginger",
            new AliasedBlockItem(ModBlocks.GINGER_CROP, new FabricItemSettings()
                    .food(ModFoodComponents.GINGER)));
    public static final Item GARLIC = registerItem("garlic",
            new AliasedBlockItem(ModBlocks.GARLIC_CROP, new FabricItemSettings()
                    .food(ModFoodComponents.GARLIC)));
    // Grain
    public static final Item BARLEY = registerItem("barley",
            new Item(new FabricItemSettings()));
    public static final Item RYE = registerItem("rye",
            new Item(new FabricItemSettings()));

    // Food Items
    public static final Item SWEET_PORRIDGE = registerItem("sweet_porridge",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item SPICED_PORRIDGE = registerItem("spiced_porridge",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item BARLEY_AND_BEEF_STEW = registerItem("barley_and_beef_stew",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item SALMON_CHOWDER = registerItem("salmon_chowder",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item MEAD_BRAISED_PORK = registerItem("mead_braised_pork",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item HERBED_COD = registerItem("herbed_cod",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item LEMON_GLAZED_CHICKEN = registerItem("lemon_glazed_chicken",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item FORAGER_FEAST = registerItem("forager_feast",
            new StewItem(new FabricItemSettings().maxCount(1)));
    public static final Item MAPLE_GLAZED_RABBIT = registerItem("maple_glazed_rabbit",
            new StewItem(new FabricItemSettings().maxCount(1)));

    // Baked Goods
    public static final Item BARLEY_BREAD = registerItem("barley_bread",
            new Item(new FabricItemSettings().food(ModFoodComponents.BARLEY_BREAD)));
    public static final Item RYE_BREAD = registerItem("rye_bread",
            new Item(new FabricItemSettings().food(ModFoodComponents.RYE_BREAD)));
    public static final Item MOLASSES_BREAD = registerItem("molasses_bread",
            new Item(new FabricItemSettings().food(ModFoodComponents.MOLASSES_BREAD)));
    public static final Item BERRY_ROLL = registerItem("berry_roll",
            new Item(new FabricItemSettings().food(ModFoodComponents.BERRY_ROLL)));
    public static final Item ROSE_TART = registerItem("rose_tart",
            new Item(new FabricItemSettings().food(ModFoodComponents.ROSE_TART)));
    public static final Item ELDERBERRY_PIE = registerItem("elderberry_pie",
            new Item(new FabricItemSettings().food(ModFoodComponents.ELDERBERRY_PIE)));
    public static final Item APPLE_PIE = registerItem("apple_pie",
            new Item(new FabricItemSettings().food(ModFoodComponents.APPLE_PIE)));

    // Mead
    public static final Item TANKARD = registerItem("tankard",
            new Item(new FabricItemSettings()));
    public static final Item HONEY_MEAD = registerItem("honey_mead",
            new Item(new FabricItemSettings().recipeRemainder(ModItems.TANKARD)));
    public static final Item MOLASSES_MEAD = registerItem("molasses_mead",
            new Item(new FabricItemSettings().recipeRemainder(ModItems.TANKARD)));
    public static final Item SOUR_MEAD = registerItem("sour_mead",
            new Item(new FabricItemSettings().recipeRemainder(ModItems.TANKARD)));
    public static final Item THORNBERRY_MEAD = registerItem("thornberry_mead",
            new Item(new FabricItemSettings().recipeRemainder(ModItems.TANKARD)));
    public static final Item BLOSSOM_MEAD = registerItem("blossom_mead",
            new Item(new FabricItemSettings().recipeRemainder(ModItems.TANKARD)));
    public static final Item AMBER_MEAD = registerItem("amber_mead",
            new Item(new FabricItemSettings().recipeRemainder(ModItems.TANKARD)));

    // Jams
    public static final Item JAR = registerItem("jar",
            new JamItem(new FabricItemSettings()));
    public static final Item APPLE_JAM = registerItem("apple_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item CHORUS_FRUIT_JAM = registerItem("chorus_fruit_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item ELDERBERRIES_JAM = registerItem("elderberries_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item GLOW_BERRIES_JAM = registerItem("glow_berries_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item GOLDEN_APPLE_JAM = registerItem("golden_apple_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item HAWTHORN_BERRIES_JAM = registerItem("hawthorn_berries_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item LEMON_JAM = registerItem("lemon_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item ROSE_HIPS_JAM = registerItem("rose_hips_jam",
            new JamItem(new FabricItemSettings()));
    public static final Item SWEET_BERRIES_JAM = registerItem("sweet_berries_jam",
            new JamItem(new FabricItemSettings()));

    // Wooden-related Items
    public static final Item MAPLE_SIGN = registerItem("maple_sign",
            new SignItem(new FabricItemSettings().maxCount(16), ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN));
    public static final Item MAPLE_HANGING_SIGN = registerItem("maple_hanging_sign",
            new HangingSignItem(ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_HANGING_WALL_SIGN, new FabricItemSettings().maxCount(16)));
    public static final Item MAPLE_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.MAPLE_BOAT_ID,
            ModBoats.MAPLE_BOAT_KEY, false);
    public static final Item MAPLE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.MAPLE_CHEST_BOAT_ID,
            ModBoats.MAPLE_BOAT_KEY, true);

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(YeastNFeastMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        YeastNFeastMod.LOGGER.info("Registering Mod Items for " + YeastNFeastMod.MOD_ID);
    }
}
