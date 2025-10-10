package net.astralya.yeastnfeast.item;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.astralya.yeastnfeast.entity.boat.ModBoatEntity;
import net.astralya.yeastnfeast.item.custom.*;
import net.astralya.yeastnfeast.item.custom.wood.ModBoatItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(YeastNFeastMod.MODID);

    // Resources
    public static final DeferredItem<Item> YEAST = ITEMS.registerSimpleItem("yeast");
    public static final DeferredItem<Item> MAPLE_SYRUP = ITEMS.registerItem("maple_syrup",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.MAPLE_SYRUP));
    public static final DeferredItem<Item> MOLASSES = ITEMS.registerItem("molasses",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.MOLASSES));
    public static final DeferredItem<Item> MILK_BOTTLE = ITEMS.registerItem("milk_bottle",
            MilkBottleItem::new, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE));
    
    // Crops & Seeds
    public static final DeferredItem<Item> MINT_SEEDS = ITEMS.register("mint_seeds",
            () -> new BlockItem(ModBlocks.MINT_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> BARLEY_SEEDS = ITEMS.register("barley_seeds",
            () -> new BlockItem(ModBlocks.BARLEY_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> RYE_SEEDS = ITEMS.register("rye_seeds",
            () -> new BlockItem(ModBlocks.RYE_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> ELDERBERRIES = ITEMS.register("elderberries",
            () -> new BlockItem(ModBlocks.ELDERBERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.ELDERBERRIES)));
    public static final DeferredItem<Item> ROSE_HIPS = ITEMS.register("rose_hips",
            () -> new BlockItem(ModBlocks.ROSE_HIPS_BUSH.get(), new Item.Properties().food(ModFoodProperties.ROSE_HIPS)));
    public static final DeferredItem<Item> LEMON = ITEMS.registerItem("lemon",
            Item::new, new Item.Properties().food(ModFoodProperties.LEMON));
    public static final DeferredItem<Item> HAWTHORN_BERRIES = ITEMS.registerItem("hawthorn_berries",
            Item::new, new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES));
    public static final DeferredItem<Item> MINT = ITEMS.registerSimpleItem("mint");
    public static final DeferredItem<Item> GINGER = ITEMS.register("ginger",
            () -> new BlockItem(ModBlocks.GINGER_CROP.get(), new Item.Properties().food(ModFoodProperties.GINGER)));
    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic",
            () -> new BlockItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties().food(ModFoodProperties.GARLIC)));
    public static final DeferredItem<Item> BARLEY = ITEMS.registerSimpleItem("barley");
    public static final DeferredItem<Item> RYE = ITEMS.registerSimpleItem("rye");

    // Bowl Food Items
    public static final DeferredItem<Item> SWEET_PORRIDGE = ITEMS.register("sweet_porridge",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SWEET_PORRIDGE).stacksTo(1),
                    () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> SPICED_PORRIDGE = ITEMS.register("spiced_porridge",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SPICED_PORRIDGE).stacksTo(1),
                    () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> BARLEY_AND_BEEF_STEW = ITEMS.register("barley_and_beef_stew",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.BARLEY_AND_BEEF_STEW).stacksTo(1),
                    () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> SALMON_CHOWDER = ITEMS.register("salmon_chowder",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SALMON_CHOWDER).stacksTo(1),
                    () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    
    // Plated Food Items
    public static final DeferredItem<Item> MEAD_BRAISED_PORK = ITEMS.register("mead_braised_pork",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MEAD_BRAISED_PORK).stacksTo(1),
                    () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> HERBAL_COD = ITEMS.register("herbal_cod",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.HERBAL_COD).stacksTo(1),
                    () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> LEMON_GLAZED_CHICKEN = ITEMS.register("lemon_glazed_chicken",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.LEMON_GLAZED_CHICKEN).stacksTo(1),
                    () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> FORAGER_FEAST = ITEMS.register("forager_feast",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.FORAGER_FEAST).stacksTo(1),
                    () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> MAPLE_GLAZED_RABBIT = ITEMS.register("maple_glazed_rabbit",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT).stacksTo(1),
                    () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));

    // 0ther Food Items
    public static final DeferredItem<Item> BARLEY_BREAD = ITEMS.registerItem("barley_bread",
            Item::new, new Item.Properties().food(ModFoodProperties.BARLEY_BREAD));
    public static final DeferredItem<Item> RYE_BREAD = ITEMS.registerItem("rye_bread",
            Item::new, new Item.Properties().food(ModFoodProperties.RYE_BREAD));
    public static final DeferredItem<Item> MOLASSES_BREAD = ITEMS.registerItem("molasses_bread",
            Item::new, new Item.Properties().food(ModFoodProperties.MOLASSES_BREAD));
    public static final DeferredItem<Item> BERRY_ROLL = ITEMS.registerItem("berry_roll",
            Item::new, new Item.Properties().food(ModFoodProperties.BERRY_ROLL));
    public static final DeferredItem<Item> ROSE_TART = ITEMS.registerItem("rose_tart",
            Item::new, new Item.Properties().food(ModFoodProperties.ROSE_TART));
    public static final DeferredItem<Item> ELDERBERRY_PIE = ITEMS.registerItem("elderberry_pie",
            Item::new, new Item.Properties().food(ModFoodProperties.ELDERBERRY_PIE));
    public static final DeferredItem<Item> APPLE_PIE = ITEMS.registerItem("apple_pie",
            Item::new, new Item.Properties().food(ModFoodProperties.APPLE_PIE));

    // Mead Items
    public static final DeferredItem<Item> TANKARD = ITEMS.registerSimpleItem("tankard");
    public static final DeferredItem<Item> HONEY_MEAD = ITEMS.register("honey_mead",
            () -> new MeadItem(new Item.Properties().stacksTo(16), () -> MobEffects.DAMAGE_RESISTANCE, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> MOLASSES_MEAD = ITEMS.register("molasses_mead",
            () -> new MeadItem(new Item.Properties().stacksTo(16), () -> MobEffects.DAMAGE_RESISTANCE, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> SOUR_MEAD = ITEMS.register("sour_mead",
            () -> new MeadItem(new Item.Properties().stacksTo(16), () -> MobEffects.DAMAGE_BOOST, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sour").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> THORNBERRY_MEAD = ITEMS.register("thornberry_mead",
            () -> new MeadItem(new Item.Properties().stacksTo(16), () -> MobEffects.DAMAGE_BOOST, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sour").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> BLOSSOM_MEAD = ITEMS.register("blossom_mead",
            () -> new MeadItem(new Item.Properties().stacksTo(16), () -> MobEffects.REGENERATION, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.floral").withStyle(ChatFormatting.BLUE)));
    public static final DeferredItem<Item> AMBER_MEAD = ITEMS.register("amber_mead",
            () -> new MeadItem(new Item.Properties().stacksTo(16), () -> MobEffects.REGENERATION, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.floral").withStyle(ChatFormatting.BLUE)));

    // Jams
    public static final DeferredItem<Item> JAR = ITEMS.registerSimpleItem("jar");
    public static final DeferredItem<Item> APPLE_JAM = ITEMS.registerItem("apple_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.APPLE_JAM).stacksTo(16));
    public static final DeferredItem<Item> CHORUS_FRUIT_JAM = ITEMS.registerItem("chorus_fruit_jam",
            ChorusFruitJamItem::new, new Item.Properties().food(ModFoodProperties.CHORUS_FRUIT_JAM).stacksTo(16));
    public static final DeferredItem<Item> ELDERBERRIES_JAM = ITEMS.registerItem("elderberries_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.ELDERBERRIES_JAM).stacksTo(16));
    public static final DeferredItem<Item> GLOW_BERRIES_JAM = ITEMS.registerItem("glow_berries_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.GLOW_BERRIES_JAM).stacksTo(16));
    public static final DeferredItem<Item> GOLDEN_APPLE_JAM = ITEMS.registerItem("golden_apple_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.GOLDEN_APPLE_JAM).stacksTo(16));
    public static final DeferredItem<Item> HAWTHORN_BERRIES_JAM = ITEMS.registerItem("hawthorn_berries_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES_JAM).stacksTo(16));
    public static final DeferredItem<Item> LEMON_JAM = ITEMS.registerItem("lemon_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.LEMON_JAM).stacksTo(16));
    public static final DeferredItem<Item> MELON_JAM = ITEMS.registerItem("melon_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.MELON_JAM).stacksTo(16));
    public static final DeferredItem<Item> ROSE_HIPS_JAM = ITEMS.registerItem("rose_hips_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.ROSE_HIPS_JAM).stacksTo(16));
    public static final DeferredItem<Item> SWEET_BERRIES_JAM = ITEMS.registerItem("sweet_berries_jam",
            ConsumableItem::new, new Item.Properties().food(ModFoodProperties.SWEET_BERRIES_JAM).stacksTo(16));

    // Wood-Related Items
    public static final DeferredItem<Item> MAPLE_BOAT = ITEMS.register("maple_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MAPLE, new Item.Properties()));
    public static final DeferredItem<Item> MAPLE_CHEST_BOAT = ITEMS.register("maple_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MAPLE, new Item.Properties()));
    public static final DeferredItem<Item> MAPLE_SIGN = ITEMS.register("maple_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MAPLE_SIGN.get(), ModBlocks.MAPLE_WALL_SIGN.get()));
    public static final DeferredItem<Item> MAPLE_HANGING_SIGN = ITEMS.register("maple_hanging_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MAPLE_HANGING_SIGN.get(), ModBlocks.MAPLE_HANGING_WALL_SIGN.get()));

    // Block Items
    public static final DeferredItem<Item> KEG = ITEMS.register("keg",
            () -> new BlockItem(ModBlocks.KEG.get(), new Item.Properties()));

    // Addon or Compat Items
    public static DeferredItem<Item> CHILLBERRIES_JAM;
    public static DeferredItem<Item> FROSTGALE_MEAD;
    public static DeferredItem<Item> SUNFIRE_TOMATO_BRUSCHETTA;
    public static DeferredItem<Item> STUFFED_MANDRAKE;
    static {
        if (ModList.get().isLoaded("hexalia")) {
            CHILLBERRIES_JAM = ITEMS.register("chillberries_jam", () ->
                    new ConsumableItem(new Item.Properties().food(ModFoodProperties.CHILLBERRIES_JAM)));
            STUFFED_MANDRAKE = ITEMS.register("stuffed_mandrake", () -> new FeastItem(new Item.Properties().food(ModFoodProperties.STUFFED_MANDRAKE),
                    () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
            FROSTGALE_MEAD =  ITEMS.register("frostgale_mead", () -> new MeadItem(new Item.Properties().stacksTo(16),
                    () -> MobEffects.DAMAGE_RESISTANCE, 4800, 1, Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
            SUNFIRE_TOMATO_BRUSCHETTA = ITEMS.register("sunfire_tomato_bruschetta", () -> new Item(new Item.Properties().food(ModFoodProperties.SUNFIRE_TOMATO_BRUSCHETTA)));
        }
    }

    public static DeferredItem<Item> GARDEN_SOUP;
    public static DeferredItem<Item> GINGER_TEA;
    public static DeferredItem<Item> SPICED_FLATBREAD;
    static {
        if (ModList.get().isLoaded("farmersdelight")) {
            GARDEN_SOUP = ITEMS.register("garden_soup", () -> new FeastItem(new Item.Properties().food(ModFoodProperties.GARDEN_SOUP),
                    () -> ModMobEffects.OVERFED, 4800, 0, Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
            GINGER_TEA =  ITEMS.register("ginger_tea", () -> new MilkBottleItem(new Item.Properties().food(ModFoodProperties.GINGER_TEA)));
            SPICED_FLATBREAD = ITEMS.register("spiced_flatbread", () -> new Item(new Item.Properties().food(ModFoodProperties.SPICED_FLATBREAD)));
        }
    }

    public static DeferredItem<Item> HOMESTEADERS_HANDBOOK;
    static {
        if (ModList.get().isLoaded("patchouli")) {
            HOMESTEADERS_HANDBOOK = ITEMS.register("homesteaders_handbook",
                    () -> new GuideBookItem(new Item.Properties()));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
