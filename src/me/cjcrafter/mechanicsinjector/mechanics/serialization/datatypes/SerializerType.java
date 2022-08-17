package me.cjcrafter.mechanicsinjector.mechanics.serialization.datatypes;

import me.cjcrafter.mechanicsinjector.MechanicsInjector;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.Argument;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.MechanicListSerializer;
import me.cjcrafter.mechanicsinjector.mechanics.serialization.StringSerializable;
import me.deecaad.core.MechanicsCore;
import me.deecaad.core.utils.ReflectionUtil;
import me.deecaad.core.utils.StringUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.deecaad.core.MechanicsCore.debug;

import org.intellij.lang.annotations.Language;

public class SerializerType<T extends StringSerializable<T>> extends DataType<T> {

    private final Class<T> clazz;
    @Language("RegExp") private String nameMatcher;

    public SerializerType(Class<T> clazz, String name) {
        super(name);

        this.clazz = clazz;
        this.nameMatcher = "[^( ]+";
    }

    public String getNameMatcher() {
        return nameMatcher;
    }

    public void setNameMatcher(@Language("RegExp") String nameMatcher) {
        this.nameMatcher = nameMatcher;
    }

    @Override
    public T serialize(String str) {

        // List storing other possible options, in case
        // the user has a spelling error. We can use this list
        // to provide useful information for the user later
        List<String> options = new ArrayList<>();

        if (Modifier.isAbstract(clazz.getModifiers())) {

            String inputName = StringUtils.match(nameMatcher, str);

            if (inputName == null) {
                debug.error("Failed to find a serializer name from " + str,
                        "Make sure you have a name before your arguments!",
                        "Example: potion(<YOUR ARGUMENTS HERE>)");

                return null;
            }

             // Get all subclasses of the string serializable
             // determine which class to serialize
             // serialize it, then return
            for (Class<StringSerializable> serializable : MechanicsInjector.getInstance().getSerializers().values()) {

                // Check if the class is a subclass of this serializer's class
                if (serializable.isAssignableFrom(clazz)) {

                    String name = StringSerializable.parseName(serializable);
                    if (name.equals(inputName)) {
                        Argument[] args = StringSerializable.parseArgs(serializable);
                        Map<String, Object> data = MechanicListSerializer.getArguments(name, str, args);
                        return ReflectionUtil.newInstance(clazz).serialize(data);
                    }

                    options.add(name);
                }
            }

            debug.error("Unknown serializer: " + inputName, "Did you mean " + StringUtils.didYouMean(inputName, options));
            return null;
        } else {

            String name = StringSerializable.parseName(clazz);
            Argument[] args = StringSerializable.parseArgs(clazz);
            Map<String, Object> data = MechanicListSerializer.getArguments(name, str, args);
            return ReflectionUtil.newInstance(clazz).serialize(data);
        }
    }

    @Override
    public boolean validate(String str) {

        // It's a bit challenging to validate this, will work on that later
        return true;
    }
}
