package net.grapes.yeastnfeast.item;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.world.item.Item;
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
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLASSES = ITEMS.register("molasses",
            () -> new Item(new Item.Properties()));
    // Fruits & Berries
    public static final RegistryObject<Item> ELDERBERRIES = ITEMS.register("elderberries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ELDERBERRIES)));
    public static final RegistryObject<Item> HAWTHORN_BERRIES = ITEMS.register("hawthorn_berries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES)));
    public static final RegistryObject<Item> ROSE_HIPS = ITEMS.register("rose_hips",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROSE_HIPS)));
    // Herbs & Spices
    public static final RegistryObject<Item> MINT = ITEMS.register("mint",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GINGER = ITEMS.register("ginger",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA = ITEMS.register("vanilla",
            () -> new Item(new Item.Properties()));
    // Grain
    public static final RegistryObject<Item> BARLEY = ITEMS.register("barley",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RYE = ITEMS.register("rye",
            () -> new Item(new Item.Properties()));
    // Mead
    public static final RegistryObject<Item> TANKARD = ITEMS.register("tankard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MEAD = ITEMS.register("mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRY_MEAD = ITEMS.register("elderberry_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HAWTHORN_MEAD = ITEMS.register("hawthorn_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_HIPS_MEAD = ITEMS.register("rose_hips_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPICED_MEAD = ITEMS.register("spiced_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLASSES_MEAD = ITEMS.register("molasses_mead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAPLE_MEAD = ITEMS.register("maple_mead",
            () -> new Item(new Item.Properties()));
    // Baked Goods
    public static final RegistryObject<Item> BARLEY_BREAD = ITEMS.register("barley_bread",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RYE_BREAD = ITEMS.register("rye_bread",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHOCOLATE_BUN = ITEMS.register("chocolate_bun",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BERRY_ROLL = ITEMS.register("berry_roll",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_HIPS_PIE = ITEMS.register("rose_hips_pie",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRY_PIE = ITEMS.register("elderberry_pie",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
