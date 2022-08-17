package me.cjcrafter.mechanicsinjector.mechanics.types;

import me.cjcrafter.mechanicsinjector.mechanics.Mechanic;
import me.cjcrafter.mechanicsinjector.mechanics.casters.MechanicCaster;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.SerializerData;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Map;

@SerializerData(name = "damage", args = "amount~DOUBLE")
public class DamageMechanic extends Mechanic {
    
    private double amount;
    
    /**
     * Default constructor for serializer
     */
    public DamageMechanic() {
    }
    
    @Override
    public Mechanic serialize(Map<String, Object> data) {
        amount = (double) data.getOrDefault("amount", 0.0);
        return super.serialize(data);
    }
    
    @Override
    public void cast(MechanicCaster caster, Location target) {
        // Do nothing...
    }
    
    @Override
    public void cast(MechanicCaster caster, Entity target) {
        if (target.getType().isAlive()) {
            LivingEntity toDamage = (LivingEntity) target;
            Entity entity = caster.getEntity();

            // entity can be null in this situation, so we
            // don't need to do a null check
            toDamage.damage(amount, entity);
        }
    }
    
    @Override
    public void cast(MechanicCaster caster, Player target) {
        Entity entity = caster.getEntity();
        target.damage(amount, entity);
    }

    @Override
    public String toString() {
        return "DamageMechanic{" +
                "amount=" + amount +
                ", delay=" + delay +
                ", repeatAmount=" + repeatAmount +
                ", repeatInterval=" + repeatInterval +
                '}';
    }
}
