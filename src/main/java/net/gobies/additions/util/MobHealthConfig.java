package net.gobies.additions.util;

import net.gobies.additions.Config;

public class MobHealthConfig {
    private final double commonMinHP;
    private final double commonMaxHP;
    private final double commonFlatHP;
    private final double uncommonMinHP;
    private final double uncommonMaxHP;
    private final double uncommonFlatHP;
    private final double rareMinHP;
    private final double rareMaxHP;
    private final double rareFlatHP;
    private final double epicMinHP;
    private final double epicMaxHP;
    private final double epicFlatHP;
    private final double legendaryMinHP;
    private final double legendaryMaxHP;
    private final double legendaryFlatHP;
    private final double shinyMinHP;
    private final double shinyMaxHP;
    private final double shinyFlatHP;

    public MobHealthConfig() {
        this.commonMinHP = Config.COMMON_HP_PERCENTAGE_MIN.get();
        this.commonMaxHP = Config.COMMON_HP_PERCENTAGE_MAX.get();
        this.commonFlatHP = Config.COMMON_FLAT_HP_INCREASE.get();
        this.uncommonMinHP = Config.UNCOMMON_HP_PERCENTAGE_MIN.get();
        this.uncommonMaxHP = Config.UNCOMMON_HP_PERCENTAGE_MAX.get();
        this.uncommonFlatHP = Config.UNCOMMON_FLAT_HP_INCREASE.get();
        this.rareMinHP = Config.RARE_HP_PERCENTAGE_MIN.get();
        this.rareMaxHP = Config.RARE_HP_PERCENTAGE_MAX.get();
        this.rareFlatHP = Config.RARE_FLAT_HP_INCREASE.get();
        this.epicMinHP = Config.EPIC_HP_PERCENTAGE_MIN.get();
        this.epicMaxHP = Config.EPIC_HP_PERCENTAGE_MAX.get();
        this.epicFlatHP = Config.EPIC_FLAT_HP_INCREASE.get();
        this.legendaryMinHP = Config.LEGENDARY_HP_PERCENTAGE_MIN.get();
        this.legendaryMaxHP = Config.LEGENDARY_HP_PERCENTAGE_MAX.get();
        this.legendaryFlatHP = Config.LEGENDARY_FLAT_HP_INCREASE.get();
        this.shinyMinHP = Config.SHINY_HP_PERCENTAGE_MIN.get();
        this.shinyMaxHP = Config.SHINY_HP_PERCENTAGE_MAX.get();
        this.shinyFlatHP = Config.SHINY_FLAT_HP_INCREASE.get();
    }

    public double getCommonMinHP() {
        return commonMinHP;
    }

    public double getCommonMaxHP() {
        return commonMaxHP;
    }

    public double getCommonFlatHP() {
        return commonFlatHP;
    }

    public double getUncommonMinHP() {
        return uncommonMinHP;
    }

    public double getUncommonMaxHP() {
        return uncommonMaxHP;
    }

    public double getUncommonFlatHP() {
        return uncommonFlatHP;
    }

    public double getRareMinHP() {
        return rareMinHP;
    }

    public double getRareMaxHP() {
        return rareMaxHP;
    }

    public double getRareFlatHP() {
        return rareFlatHP;
    }

    public double getEpicMinHP() {
        return epicMinHP;
    }

    public double getEpicMaxHP() {
        return epicMaxHP;
    }

    public double getEpicFlatHP() {
        return epicFlatHP;
    }

    public double getLegendaryMinHP() {
        return legendaryMinHP;
    }

    public double getLegendaryMaxHP() {
        return legendaryMaxHP;
    }

    public double getLegendaryFlatHP() {
        return legendaryFlatHP;
    }

    public double getShinyMinHP() {
        return shinyMinHP;
    }

    public double getShinyMaxHP() {
        return shinyMaxHP;
    }

    public double getShinyFlatHP() {
        return shinyFlatHP;
    }
}