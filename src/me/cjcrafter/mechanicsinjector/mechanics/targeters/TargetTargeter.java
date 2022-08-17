package me.cjcrafter.mechanicsinjector.mechanics.targeters;

import me.cjcrafter.mechanicsinjector.mechanics.casters.MechanicCaster;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.SerializerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static me.deecaad.core.MechanicsCore.debug;

/**
 * If the <code>MechanicCaster</code> is a <code>Mob</code>, and the
 * <code>Mob</code> has a target, then this will target that target
 *
 * Otherwise, nothing is targeted
 */
@SerializerData(name = "@target")
public class TargetTargeter implements Targeter<LivingEntity> {

    /**
     * Default constructor for serializers
     */
    public TargetTargeter() {
    }

    @Override
    public List<LivingEntity> getTargets(MechanicCaster caster) {

        // Since this targeter is a bit weird, I am making sure to tell users if they are using it wrong
        if (caster.getEntity() != null) {
            Entity entity = caster.getEntity();

            if (entity instanceof Mob) {
                LivingEntity mob = ((Mob) entity).getTarget();
                if (mob != null) return Collections.singletonList(mob);
                else debug.debug(entity + " can have a target, but doesn't currently have one");
            } else {
                debug.debug(entity.getType() + " can not have targets");
            }
        } else {
            debug.warn("Tried to use @target while not using an entity to cast the mechanic");
        }

        return null;
    }

    @Override
    public Targeter<LivingEntity> serialize(Map data) {
        // Do nothing...
        return this;
    }
}
