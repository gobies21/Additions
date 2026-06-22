package net.gobies.additions.util;

import net.gobies.additions.config.CommonConfig;

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
        this.commonMinHP = CommonConfig.COMMON_HP_PERCENTAGE_MIN.get();
        this.commonMaxHP = CommonConfig.COMMON_HP_PERCENTAGE_MAX.get();
        this.commonFlatHP = CommonConfig.COMMON_FLAT_HP_INCREASE.get();
        this.uncommonMinHP = CommonConfig.UNCOMMON_HP_PERCENTAGE_MIN.get();
        this.uncommonMaxHP = CommonConfig.UNCOMMON_HP_PERCENTAGE_MAX.get();
        this.uncommonFlatHP = CommonConfig.UNCOMMON_FLAT_HP_INCREASE.get();
        this.rareMinHP = CommonConfig.RARE_HP_PERCENTAGE_MIN.get();
        this.rareMaxHP = CommonConfig.RARE_HP_PERCENTAGE_MAX.get();
        this.rareFlatHP = CommonConfig.RARE_FLAT_HP_INCREASE.get();
        this.epicMinHP = CommonConfig.EPIC_HP_PERCENTAGE_MIN.get();
        this.epicMaxHP = CommonConfig.EPIC_HP_PERCENTAGE_MAX.get();
        this.epicFlatHP = CommonConfig.EPIC_FLAT_HP_INCREASE.get();
        this.legendaryMinHP = CommonConfig.LEGENDARY_HP_PERCENTAGE_MIN.get();
        this.legendaryMaxHP = CommonConfig.LEGENDARY_HP_PERCENTAGE_MAX.get();
        this.legendaryFlatHP = CommonConfig.LEGENDARY_FLAT_HP_INCREASE.get();
        this.shinyMinHP = CommonConfig.SHINY_HP_PERCENTAGE_MIN.get();
        this.shinyMaxHP = CommonConfig.SHINY_HP_PERCENTAGE_MAX.get();
        this.shinyFlatHP = CommonConfig.SHINY_FLAT_HP_INCREASE.get();
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