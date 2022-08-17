package me.cjcrafter.mechanicsinjector.mechanics.targeters;

import me.cjcrafter.mechanicsinjector.mechanics.casters.MechanicCaster;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.SerializerData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SerializerData(name = "self")
public class SelfTargeter implements Targeter<Object> {

    /**
     * Default constructor for serializer
     */
    public SelfTargeter() {
    }
    
    @Override
    public List<Object> getTargets(MechanicCaster caster) {
        return Collections.singletonList(caster.getEntity() == null ? caster.getLocation() : caster.getEntity());
    }
    
    @Override
    public Targeter<Object> serialize(Map<String, Object> data) {

        // Nothing needs to be done to the serializer
        return this;
    }
}
