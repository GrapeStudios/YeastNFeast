package net.grapes.yeastnfeast.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandler {

    public static final ScreenHandlerType<KegScreenHandler> KEG_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(YeastNFeastMod.MOD_ID, "keg_screen_handler"),
                    new ExtendedScreenHandlerType<>(KegScreenHandler::new));

    public static void registerScreenHandlers() {
        YeastNFeastMod.LOGGER.info("Registering Screen Handlers for " + YeastNFeastMod.MOD_ID);
    }
}
