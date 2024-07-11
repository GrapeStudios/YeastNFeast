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
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.YEAST.get())).title(Component.translatable("creative_tab.yeastnfeast")).displayItems((displayParameters, output) -> {
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
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
