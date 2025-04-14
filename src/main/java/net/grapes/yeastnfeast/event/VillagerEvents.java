package net.grapes.yeastnfeast.event;

import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.villager.ModVillagers;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;

import java.util.List;

public class VillagerEvents
{
    private static final Random RANDOM = Random.create();

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> addTrades());
    }

    public static void addTrades() {
        // Register trades for your custom villager
        TradeOfferHelper.registerVillagerOffers(ModVillagers.TAVERN_KEEPER, 1, trades -> {
            addLevel1Trades(trades);
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.TAVERN_KEEPER, 2, trades -> {
            trades.add(itemForEmeralds(ModBlocks.TREE_TAP, 1, 8, 2, 12));
            trades.add(itemForEmeralds(ModItems.TANKARD, 1, 4, 4, 8));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.TAVERN_KEEPER, 3, trades -> {
            addRandomMeadTrades(trades);
        });
    }

    private static void addLevel1Trades(List<TradeOffers.Factory> level1Trades) {
        level1Trades.add(itemForEmeralds(ModItems.MINT_SEEDS, 3, 3, 6, 3));

        ItemConvertible[] secondaryOptions = {
                ModItems.BARLEY_SEEDS,
                ModItems.RYE_SEEDS,
                ModItems.GINGER,
                ModItems.GARLIC
        };

        ItemConvertible selectedOption = secondaryOptions[RANDOM.nextInt(secondaryOptions.length)];
        level1Trades.add(itemForEmeralds(selectedOption, 3, 3, 6, 3));
    }

    private static void addRandomMeadTrades(List<TradeOffers.Factory> level3Trades) {
        ItemConvertible[] meads = {
                ModItems.BLOSSOM_MEAD,
                ModItems.AMBER_MEAD,
                ModItems.MOLASSES_MEAD,
                ModItems.SOUR_MEAD,
                ModItems.HONEY_MEAD,
                ModItems.THORNBERRY_MEAD
        };

        int firstIndex = RANDOM.nextInt(meads.length);
        int secondIndex;
        do {
            secondIndex = RANDOM.nextInt(meads.length);
        } while (secondIndex == firstIndex);

        level3Trades.add(itemForEmeralds(meads[firstIndex], 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(meads[secondIndex], 1, 2, 2, 8));
    }

    public static TradeOffers.Factory emeraldForItems(ItemConvertible item, int emeraldCost, int itemCount, int maxTrades, int xp) {
        return (entity, random) -> new TradeOffer(
                new ItemStack(item, itemCount),
                new ItemStack(Items.EMERALD, emeraldCost),
                maxTrades, xp, 0.05F
        );
    }

    public static TradeOffers.Factory itemForEmeralds(ItemConvertible item, int itemCount, int emeraldCost, int maxTrades, int xp) {
        return (entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, emeraldCost),
                new ItemStack(item, itemCount),
                maxTrades, xp, 0.05F
        );
    }
}