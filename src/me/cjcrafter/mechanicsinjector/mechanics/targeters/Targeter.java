package me.cjcrafter.mechanicsinjector.mechanics.targeters;


import me.cjcrafter.mechanicsinjector.mechanics.casters.MechanicCaster;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.StringSerializable;

import javax.annotation.Nullable;
import java.util.List;

public interface Targeter<T> extends StringSerializable<Targeter<T>> {

    @Nullable
    List<T> getTargets(MechanicCaster caster);
}
