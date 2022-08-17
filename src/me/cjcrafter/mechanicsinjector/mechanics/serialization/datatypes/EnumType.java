package me.cjcrafter.mechanicsinjector.mechanics.serialization.datatypes;

import com.google.common.base.Enums;

/**
 * Matches any enum type
 *
 * @param <T> The enum type
 */
public class EnumType<T extends Enum<T>> extends DataType<T> {

    private final Class<T> clazz;

    public EnumType(Class<T> clazz, String name) {
        super(name);

        this.clazz = clazz;
    }

    @Override
    public T serialize(String str) {
        return Enum.valueOf(clazz, str.trim().toUpperCase());
    }

    @Override
    public boolean validate(String str) {
        return Enums.getIfPresent(clazz, str.trim().toUpperCase()).isPresent();
    }
}
