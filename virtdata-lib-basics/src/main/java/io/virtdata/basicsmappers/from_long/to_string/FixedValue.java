package io.virtdata.basicsmappers.from_long.to_string;

import io.virtdata.annotations.ThreadSafeMapper;

import java.util.function.LongFunction;

/**
 * Simply throws away the input value and returns a fixed String.
 */
@ThreadSafeMapper
public class FixedValue implements LongFunction<String> {

    private final String fixedValue;

    /**
     * @param fixedValue a value which will be returned on ever call
     */
    public FixedValue(String fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Override
    public String apply(long value) {
        return fixedValue;
    }
}
