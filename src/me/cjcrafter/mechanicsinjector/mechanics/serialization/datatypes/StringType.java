package me.cjcrafter.mechanicsinjector.mechanics.serialization.datatypes;

/**
 * Matches any input
 */
public class StringType extends DataType<String> {

    public StringType() {
        super("STRING");
    }

    @Override
    public String serialize(String str) {
        return str;
    }

    @Override
    public boolean validate(String str) {
        return true;
    }
}
