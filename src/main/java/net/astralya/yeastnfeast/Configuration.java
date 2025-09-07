package net.astralya.yeastnfeast;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {

    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec CLIENT_CONFIG;

    public static final String FUNCTIONAL_BLOCKS = "functional_blocks";
    public static ModConfigSpec.IntValue TREE_TAP_DRIP_DELAY;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        // --- CATEGORY: Dreamcatcher
        COMMON_BUILDER.comment("Functional Blocks Settings").push(FUNCTIONAL_BLOCKS);
        COMMON_BUILDER.comment("Tree Tap Settings").push(FUNCTIONAL_BLOCKS);
        TREE_TAP_DRIP_DELAY = COMMON_BUILDER
                .comment("Ticks required before the Tree Tap begins dripping (default: 7200 ticks = 6 minutes)")
                .defineInRange("dripDelayTicks", 7200, 20, 24000);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
