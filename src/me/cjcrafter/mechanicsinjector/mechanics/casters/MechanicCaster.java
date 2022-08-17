package me.cjcrafter.mechanicsinjector.mechanics.casters;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface MechanicCaster {

    @Nonnull
    Location getLocation();

    @Nonnull
    Object getTrigger();

    @Nullable
    Entity getEntity();
}
