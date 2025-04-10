package net.grapes.yeastnfeast.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = YeastNFeastMod.MOD_ID)
@ParametersAreNonnullByDefault
public class VillagerEvents
{
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() != ModVillagers.TAVERN_KEEPER.get()) {
            return;
        }

        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        // Level 1 trades - Seeds
        addLevel1Trades(trades.get(1));

        // Level 2 trades - Tools
        trades.get(2).add(itemForEmeralds(ModBlocks.TREE_TAP.get(), 1, 8, 2, 12));
        trades.get(2).add(itemForEmeralds(ModItems.TANKARD.get(), 1, 4, 4, 8));

        // Level 3 trades - Random selection of mead items
        addRandomMeadTrades(trades.get(3));
    }

    private static void addLevel1Trades(List<VillagerTrades.ItemListing> level1Trades) {
        level1Trades.add(itemForEmeralds(ModItems.MINT_SEEDS.get(), 3, 3, 6, 3));

        ItemLike[] secondaryOptions = {
                ModItems.BARLEY_SEEDS.get(),
                ModItems.RYE_SEEDS.get(),
                ModItems.GINGER.get(),
                ModItems.GARLIC.get()
        };

        ItemLike selectedOption = secondaryOptions[RANDOM.nextInt(secondaryOptions.length)];
        level1Trades.add(itemForEmeralds(selectedOption, 3, 3, 6, 3));
    }

    private static void addRandomMeadTrades(List<VillagerTrades.ItemListing> level3Trades) {
        ItemLike[] meads = {
                ModItems.BLOSSOM_MEAD.get(),
                ModItems.AMBER_MEAD.get(),
                ModItems.MOLASSES_MEAD.get(),
                ModItems.SOUR_MEAD.get(),
                ModItems.HONEY_MEAD.get(),
                ModItems.THORNBERRY_MEAD.get()
        };

        int firstIndex = RANDOM.nextInt(meads.length);
        int secondIndex;
        do {
            secondIndex = RANDOM.nextInt(meads.length);
        } while (secondIndex == firstIndex);

        level3Trades.add(itemForEmeralds(meads[firstIndex], 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(meads[secondIndex], 1, 2, 2, 8));
    }

    public static BasicItemListing emeraldForItems(ItemLike item, int emeraldCost, int itemCount, int maxTrades, int xp) {
        return new BasicItemListing(new ItemStack(item, itemCount), new ItemStack(Items.EMERALD, emeraldCost), maxTrades, xp, 0.05F);
    }

    public static BasicItemListing itemForEmeralds(ItemLike item, int itemCount, int emeraldCost, int maxTrades, int xp) {
        return new BasicItemListing(new ItemStack(Items.EMERALD, emeraldCost), new ItemStack(item, itemCount), maxTrades, xp, 0.05F);
    }
}