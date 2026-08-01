package com.hjsmc.bksr;

import net.minecraftforge.common.ForgeConfigSpec;

public final class BookScrollerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static final ForgeConfigSpec.IntValue NORMAL_PAGES = BUILDER
            .comment("Pages to turn for one mouse wheel step")
            .defineInRange("normalPages", 1, 1, 10);

    static final ForgeConfigSpec.IntValue SHIFT_PAGES = BUILDER
            .comment("Pages to turn for one mouse wheel step while holding Shift")
            .defineInRange("shiftPages", 3, 1, 10);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private BookScrollerConfig() {
    }

    public static int normalPages() {
        return NORMAL_PAGES.get();
    }

    public static int shiftPages() {
        return SHIFT_PAGES.get();
    }

    public static void save(int normalPages, int shiftPages) {
        NORMAL_PAGES.set(clamp(normalPages));
        SHIFT_PAGES.set(clamp(shiftPages));
        SPEC.save();
    }

    private static int clamp(int value) {
        return Math.max(1, Math.min(10, value));
    }
}
