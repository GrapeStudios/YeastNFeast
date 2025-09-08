package net.astralya.yeastnfeast.screen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.screen.custom.KegScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<KegScreenHandler> KEG_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(YeastNFeastMod.MODID, "keg_screen"),
                    new ExtendedScreenHandlerType<>(KegScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        YeastNFeastMod.LOGGER.info("Registering Screen Handlers for " + YeastNFeastMod.MODID);
    }
}