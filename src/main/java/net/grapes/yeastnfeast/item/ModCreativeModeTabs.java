package net.grapes.yeastnfeast.item;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YeastNFeastMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> YEASTNFEAST_TAB = CREATIVE_MODE_TABS.register("yeastnfeast_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.TANKARD.get())).title(Component.translatable("creative_tab.yeastnfeast")).displayItems((displayParameters, output) -> {
                output.accept(ModItems.YEAST.get());
                output.accept(ModItems.MAPLE_SYRUP.get());
                output.accept(ModItems.MOLASSES.get());
                output.accept(ModItems.ELDERBERRIES.get());
                output.accept(ModItems.HAWTHORN_BERRIES.get());
                output.accept(ModItems.ROSE_HIPS.get());
                output.accept(ModItems.MINT.get());
                output.accept(ModItems.GINGER.get());
                output.accept(ModItems.VANILLA.get());
                output.accept(ModItems.BARLEY.get());
                output.accept(ModItems.RYE.get());
                output.accept(ModItems.TANKARD.get());
                output.accept(ModItems.MEAD.get());
                output.accept(ModItems.ELDERBERRY_MEAD.get());
                output.accept(ModItems.HAWTHORN_MEAD.get());
                output.accept(ModItems.ROSE_HIPS_MEAD.get());
                output.accept(ModItems.SPICED_MEAD.get());
                output.accept(ModItems.MOLASSES_MEAD.get());
                output.accept(ModItems.MAPLE_MEAD.get());
                output.accept(ModItems.BARLEY_BREAD.get());
                output.accept(ModItems.RYE_BREAD.get());
                output.accept(ModItems.ROSE_PIE.get());
                output.accept(ModItems.ELDERBERRY_PIE.get());
                output.accept(ModItems.APPLE_PIE.get());
                output.accept(ModItems.CHOCOLATE_BUN.get());
                output.accept(ModItems.BERRY_ROLL.get());
                output.accept(ModItems.BARLEY_SEEDS.get());
                output.accept(ModItems.RYE_SEEDS.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
