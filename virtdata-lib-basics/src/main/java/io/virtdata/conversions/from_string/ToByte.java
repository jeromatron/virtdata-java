package io.virtdata.conversions.from_string;

import io.virtdata.annotations.ThreadSafeMapper;

import java.util.function.Function;

@ThreadSafeMapper
public class ToByte implements Function<String,Byte> {
    @Override
    public Byte apply(String input) {
        return Byte.valueOf(input);
    }
}
