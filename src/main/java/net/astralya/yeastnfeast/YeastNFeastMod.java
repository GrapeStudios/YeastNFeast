package net.astralya.yeastnfeast;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.effect.ModEffects;
import net.astralya.yeastnfeast.entity.boat.ModBoats;
import net.astralya.yeastnfeast.item.ModItemGroups;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.ModScreenHandlers;
import net.astralya.yeastnfeast.util.ModRegistries;
import net.astralya.yeastnfeast.villager.ModVillagers;
import net.astralya.yeastnfeast.villager.VillagerEvents;
import net.astralya.yeastnfeast.worldgen.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YeastNFeastMod implements ModInitializer {

	public static final String MODID = "yeastnfeast";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntityTypes.registerBlockEntities();
        ModItemGroups.registerItemGroups();
        ModRegistries.registerModStuff();
        ModEffects.registerEffects();
        ModBoats.registerBoats();
        ModRecipes.registerRecipes();
        ModScreenHandlers.registerScreenHandlers();
        ModWorldGeneration.generateModWorldGeneration();
        ModVillagers.registerVillagers();
        VillagerEvents.init();
    }
}