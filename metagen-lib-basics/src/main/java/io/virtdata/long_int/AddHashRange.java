package io.virtdata.long_int;

import io.virtdata.long_long.HashRange;

import java.util.function.LongToIntFunction;

public class AddHashRange implements LongToIntFunction {

    private final io.virtdata.long_long.HashRange hashRange;

    public AddHashRange(long maxValue) {
        this(0, maxValue);
    }

    public AddHashRange(long minValue, long maxValue) {
        this.hashRange = new HashRange(minValue, maxValue);
    }

    @Override
    public int applyAsInt(long operand) {
        return (int) ((operand + hashRange.applyAsLong(operand)) & Integer.MAX_VALUE);
    }
}