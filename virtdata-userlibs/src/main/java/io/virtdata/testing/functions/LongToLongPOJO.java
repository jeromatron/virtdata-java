package io.virtdata.testing.functions;

import java.util.function.LongFunction;

public class LongToLongPOJO implements LongFunction<ARandomPOJO> {

    @Override
    public ARandomPOJO apply(long value) {
        return new ARandomPOJO(value);
    }
}
