package net.grapes.yeastnfeast.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent ROSE_HIPS = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build();
    public static final FoodComponent ELDERBERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build();
    public static final FoodComponent HAWTHORN_BERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build();

    public static final FoodComponent BARLEY_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent RYE_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent MOLASSES_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();

    public static final FoodComponent MAPLE_SYRUP = new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).alwaysEdible().build();
    public static final FoodComponent MOLASSES = new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).alwaysEdible().build();

    public static final FoodComponent BERRY_ROLL = new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).build();
    public static final FoodComponent ROSE_TART = new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).build();
    public static final FoodComponent ELDERBERRY_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).build();
    public static final FoodComponent APPLE_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).build();

    public static final FoodComponent GARLIC = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent GINGER = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent LEMON = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
}
