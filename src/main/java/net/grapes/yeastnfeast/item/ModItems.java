package net.grapes.yeastnfeast.item;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.custom.MilkBottleItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YeastNFeastMod.MOD_ID);

    // Resources
    public static final RegistryObject<Item> YEAST = ITEMS.register("yeast",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAPLE_SYRUP = ITEMS.register("maple_syrup",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MAPLE_SYRUP).stacksTo(16)));
    public static final RegistryObject<Item> MOLASSES = ITEMS.register("molasses",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MOLASSES).stacksTo(16)));
    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

    // Seeds
    public static final RegistryObject<Item> BARLEY_SEEDS = ITEMS.register("barley_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BARLEY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RYE_SEEDS = ITEMS.register("rye_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RYE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> MINT_SEEDS = ITEMS.register("mint_seeds",
            () -> new ItemNameBlockItem(ModBlocks.MINT_CROP.get(), new Item.Properties()));

    // Fruits & Berries
    public static final RegistryObject<Item> ELDERBERRIES = ITEMS.register("elderberries",
            () -> new BlockItem(ModBlocks.ELDERBERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.ELDERBERRIES)));
    public static final RegistryObject<Item> HAWTHORN_BERRIES = ITEMS.register("hawthorn_berries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES)));
    public static final RegistryObject<Item> ROSE_HIPS = ITEMS.register("rose_hips",
            () -> new BlockItem(ModBlocks.ROSE_HIPS_BUSH.get(), new Item.Properties().food(ModFoodProperties.ROSE_HIPS)));
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",
            () -> new Item(new Item.Properties().food(ModFoodProperties.LEMON)));

    // Herbs & Spices
    public static final RegistryObject<Item> MINT = ITEMS.register("mint",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GINGER = ITEMS.register("ginger",
            () -> new ItemNameBlockItem(ModBlocks.GINGER_CROP.get(), new Item.Properties()
                    .food(ModFoodProperties.GINGER)));
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic",
            () -> new ItemNameBlockItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties()
                    .food(ModFoodProperties.GARLIC)));
    // Grain
    public static final RegistryObject<Item> BARLEY = ITEMS.register("barley",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RYE = ITEMS.register("rye",
            () -> new Item(new Item.Properties()));

    // Baked Goods
    public static final RegistryObject<Item> BARLEY_BREAD = ITEMS.register("barley_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BARLEY_BREAD)));
    public static final RegistryObject<Item> RYE_BREAD = ITEMS.register("rye_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RYE_BREAD)));
    public static final RegistryObject<Item> MOLASSES_BREAD = ITEMS.register("molasses_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MOLASSES_BREAD)));
    public static final RegistryObject<Item> BERRY_ROLL = ITEMS.register("berry_roll",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BERRY_ROLL)));
    public static final RegistryObject<Item> ROSE_TART = ITEMS.register("rose_tart",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROSE_TART)));
    public static final RegistryObject<Item> ELDERBERRY_PIE = ITEMS.register("elderberry_pie",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ELDERBERRY_PIE)));
    public static final RegistryObject<Item> APPLE_PIE = ITEMS.register("apple_pie",
            () -> new Item(new Item.Properties().food(ModFoodProperties.APPLE_PIE)));

    // Mead
    public static final RegistryObject<Item> TANKARD = ITEMS.register("tankard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HONEY_MEAD = ITEMS.register("honey_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLASSES_MEAD = ITEMS.register("molasses_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SOUR_MEAD = ITEMS.register("sour_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> THORNBERRY_MEAD = ITEMS.register("thornberry_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOSSOM_MEAD = ITEMS.register("blossom_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBER_MEAD = ITEMS.register("amber_mead",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
