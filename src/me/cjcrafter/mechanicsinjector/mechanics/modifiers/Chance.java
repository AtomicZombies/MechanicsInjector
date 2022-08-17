package me.cjcrafter.mechanicsinjector.mechanics.modifiers;

import me.deecaad.core.utils.NumberUtils;

public interface Chance {

    double getChance();

    void setChance(double chance);

    default boolean testChance() {
        return NumberUtils.chance(getChance());
    }
}
