package me.cjcrafter.mechanicsinjector.mechanics.targeters;

import me.cjcrafter.mechanicsinjector.mechanics.casters.MechanicCaster;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.SerializerData;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Targets all entities in the caster's world
 */
@SerializerData(name = "@world")
public class WorldTargeter extends EntityTargeter {

    /**
     * Default constructor for serializers
     */
    public WorldTargeter() {
    }

    @Nullable
    @Override
    public List<Entity> getTargets(MechanicCaster caster) {
        List<Entity> entities = caster.getLocation().getWorld().getEntities();
        List<Entity> temp = new ArrayList<>(entities.size());

        for (Entity entity : entities) {
            if (living && entity.getType().isAlive()) continue;
            else if (only != null && only == entity.getType()) continue;

            temp.add(entity);
        }

        return temp;
    }

    @Override
    public Targeter<Entity> serialize(Map<String, Object> data) {
        return super.serialize(data);
    }
}
