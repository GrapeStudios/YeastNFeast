package net.grapes.yeastnfeast.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.fml.ModList;

public class ModFoodProperties {
    // Berries
    public static FoodProperties ROSE_HIPS = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static FoodProperties ELDERBERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static FoodProperties HAWTHORN_BERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();

    // Fruits & Others
    public static FoodProperties GARLIC = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build();
    public static FoodProperties GINGER = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build();
    public static FoodProperties LEMON = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build();
    public static FoodProperties MAPLE_SYRUP = new FoodProperties.Builder().nutrition(6).saturationMod(0.1f).build();
    public static FoodProperties MOLASSES = new FoodProperties.Builder().nutrition(6).saturationMod(0.1f).build();

    // Bread
    public static FoodProperties BARLEY_BREAD = new FoodProperties.Builder().nutrition(5).saturationMod(0.8f).build();
    public static FoodProperties RYE_BREAD = new FoodProperties.Builder().nutrition(5).saturationMod(0.8f).build();
    public static FoodProperties MOLASSES_BREAD = new FoodProperties.Builder().nutrition(5).saturationMod(0.8f).build();


    // Baked Goods
    public static FoodProperties BERRY_ROLL = new FoodProperties.Builder().nutrition(4).saturationMod(0.5f).build();
    public static FoodProperties ROSE_TART = new FoodProperties.Builder().nutrition(8).saturationMod(0.6f).build();
    public static FoodProperties ELDERBERRY_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(0.6f).build();
    public static FoodProperties APPLE_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(0.6f).build();

    // Feasts
    public static final FoodProperties SWEET_PORRIDGE = new FoodProperties.Builder().nutrition(10).saturationMod(0.6f).build();
    public static final FoodProperties SPICED_PORRIDGE = new FoodProperties.Builder().nutrition(10).saturationMod(0.6f).build();
    public static final FoodProperties BARLEY_AND_BEEF_STEW = new FoodProperties.Builder().nutrition(14).saturationMod(0.75f).build();
    public static final FoodProperties SALMON_CHOWDER = new FoodProperties.Builder().nutrition(12).saturationMod(0.8f).build();
    public static final FoodProperties MEAD_BRAISED_PORK = new FoodProperties.Builder().nutrition(14).saturationMod(0.75f).build();
    public static final FoodProperties HERBAL_COD = new FoodProperties.Builder().nutrition(10).saturationMod(0.6f).build();
    public static final FoodProperties LEMON_GLAZED_CHICKEN = new FoodProperties.Builder().nutrition(14).saturationMod(0.75f).build();
    public static final FoodProperties FORAGER_FEAST = new FoodProperties.Builder().nutrition(12).saturationMod(0.8f).build();
    public static final FoodProperties MAPLE_GLAZED_RABBIT = new FoodProperties.Builder().nutrition(10).saturationMod(0.6f).build();

    // Jams
    public static final FoodProperties APPLE_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties CHORUS_FRUIT_JAM = new FoodProperties.Builder().nutrition(7).saturationMod(0.8f).build();
    public static final FoodProperties ELDERBERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties GLOW_BERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties GOLDEN_APPLE_JAM = new FoodProperties.Builder().nutrition(8).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F)
            .alwaysEat().build();
    public static final FoodProperties HAWTHORN_BERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties LEMON_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties ROSE_HIPS_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties SWEET_BERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();
    public static final FoodProperties MELON_JAM = new FoodProperties.Builder().nutrition(6).saturationMod(0.8f).build();

    // Compat Items
    public static FoodProperties CHILLBERRIES_JAM;
    static {
        if (ModList.get().isLoaded("hexalia")) {
            CHILLBERRIES_JAM = new FoodProperties.Builder()
                    .nutrition(6).saturationMod(0.8f).build();
        }
    }
}
