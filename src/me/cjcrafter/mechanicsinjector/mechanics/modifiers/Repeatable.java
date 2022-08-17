package me.cjcrafter.mechanicsinjector.mechanics.modifiers;

public interface Repeatable {

    int getRepeatAmount();

    void setRepeatAmount(int repeatAmount);

    int getRepeatInterval();

    void setRepeatInterval(int repeatInterval);
}
