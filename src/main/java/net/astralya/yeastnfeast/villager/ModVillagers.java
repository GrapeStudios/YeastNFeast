package net.astralya.yeastnfeast.villager;

import com.google.common.collect.ImmutableSet;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, YeastNFeastMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, YeastNFeastMod.MODID);

    public static final Holder<PoiType> KEG_POI = POI_TYPES.register("keg_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.KEG.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final Holder<VillagerProfession> TAVERNKEEPER = VILLAGER_PROFESSIONS.register("tavernkeeper",
            () -> new VillagerProfession("tavernkeeper", holder -> holder.value() == KEG_POI.value(),
                    holder -> holder.value() == KEG_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}