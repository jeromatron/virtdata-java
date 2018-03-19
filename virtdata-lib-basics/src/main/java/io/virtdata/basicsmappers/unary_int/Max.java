package io.virtdata.basicsmappers.unary_int;

import io.virtdata.api.ThreadSafeMapper;

import java.util.function.IntUnaryOperator;

@ThreadSafeMapper
public class Max implements IntUnaryOperator {

    private final int max;

    public Max(int max) {
        this.max = max;
    }

    @Override
    public int applyAsInt(int operand) {
        return Math.max(operand,max);
    }
}
