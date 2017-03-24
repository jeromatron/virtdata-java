package io.virtdata.longs;

import java.util.function.LongUnaryOperator;

public class CycleRange implements LongUnaryOperator {

    private final long minValue;
    private final long width;

    public CycleRange(long minValue, long maxValue) {
        this.minValue = minValue;

        if (maxValue<minValue) {
            throw new RuntimeException("CycleRange must have min and max value in that order.");
        }
        this.width = maxValue - minValue;
    }

    @Override
    public long applyAsLong(long operand) {
        return minValue + (operand % width);
    }
}
