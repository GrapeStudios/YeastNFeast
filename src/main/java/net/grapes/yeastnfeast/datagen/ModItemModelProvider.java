package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, YeastNFeastMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.YEAST);
        simpleItem(ModItems.MAPLE_SYRUP);
        simpleItem(ModItems.MOLASSES);
        simpleItem(ModItems.ELDERBERRIES);
        simpleItem(ModItems.HAWTHORN_BERRIES);
        simpleItem(ModItems.ROSE_HIPS);
        simpleItem(ModItems.MINT);
        simpleItem(ModItems.GINGER);
        simpleItem(ModItems.VANILLA);
        simpleItem(ModItems.RYE);
        simpleItem(ModItems.BARLEY);
        simpleItem(ModItems.TANKARD);
        simpleItem(ModItems.MEAD);
        simpleItem(ModItems.ELDERBERRY_MEAD);
        simpleItem(ModItems.ROSE_HIPS_MEAD);
        simpleItem(ModItems.SPICED_MEAD);
        simpleItem(ModItems.HAWTHORN_MEAD);
        simpleItem(ModItems.MOLASSES_MEAD);
        simpleItem(ModItems.MAPLE_MEAD);
        simpleItem(ModItems.ROSE_PIE);
        simpleItem(ModItems.ELDERBERRY_PIE);
        simpleItem(ModItems.RYE_BREAD);
        simpleItem(ModItems.BARLEY_BREAD);
        simpleItem(ModItems.CHOCOLATE_BUN);
        simpleItem(ModItems.BERRY_ROLL);
        simpleItem(ModItems.BARLEY_SEEDS);
        simpleItem(ModItems.RYE_SEEDS);
        simpleItem(ModItems.APPLE_PIE);
    }

    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(YeastNFeastMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
