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
    }

    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(YeastNFeastMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
