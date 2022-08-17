package me.cjcrafter.mechanicsinjector;

import me.cjcrafter.mechanicsinjector.mechanics.serialization.StringSerializable;
import me.deecaad.core.MechanicsPlugin;
import me.deecaad.core.file.JarSearcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

public class MechanicsInjector extends MechanicsPlugin {

    private static MechanicsInjector instance;

    private List<JarFile> registeredJars = new ArrayList<>();
    private Map<String, Class<StringSerializable>> serializers = new HashMap<>();

    @Override
    public void onLoad() {
        instance = this;
        super.onLoad();
    }

    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();

        for (JarFile jar : registeredJars) {
            JarSearcher searcher = new JarSearcher(jar);
            List<Class<StringSerializable>> serializers = searcher.findAllSubclasses(StringSerializable.class, true);

            for (Class<StringSerializable> serializer : serializers) {

                // Parse name and arguments now to avoid doing it later
                String name = StringSerializable.parseName(serializer);
                StringSerializable.parseArgs(serializer);

                if (this.serializers.containsKey(name)) {
                    Class<?> current = this.serializers.get(name);

                    // Checking if we should try to
                    if (!current.isAssignableFrom(serializer)) {
                        debug.error("Overriding serializer failed... Duplicate key: " + name);
                        return;
                    }
                }
                this.serializers.put(name, serializer);
            }
        }

        registeredJars = null;
    }

    public void addJar(JarFile file) {
        if (registeredJars == null)
            throw new IllegalStateException("Tried to register jarfile after MechanicsInjector was enabled! Do this onLoad");
        else if (file == null)
            throw new IllegalStateException("Tried to register null jarfile");

        registeredJars.add(file);
    }

    public static MechanicsInjector getInstance() {
        return instance;
    }
}