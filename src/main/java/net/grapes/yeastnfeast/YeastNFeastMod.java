package net.grapes.yeastnfeast;

import net.fabricmc.api.ModInitializer;

import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.entity.ModBlockEntities;
import net.grapes.yeastnfeast.entity.ModBoats;
import net.grapes.yeastnfeast.item.ModItemGroup;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.particle.ModParticles;
import net.grapes.yeastnfeast.util.ModRegistries;
import net.grapes.yeastnfeast.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YeastNFeastMod implements ModInitializer {

	public static final String MOD_ID = "yeastnfeast";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModWorldGeneration.generateModWorldGeneration();
		ModRegistries.registerModStuff();
		ModBoats.registerBoats();
		ModBlockEntities.registerBlockEntities();
		ModParticles.registerParticles();
	}
}