package io.virtdata.basicsmappers.from_long.to_long;

import io.virtdata.annotations.Categories;
import io.virtdata.annotations.Category;
import io.virtdata.annotations.Example;
import io.virtdata.annotations.ThreadSafeMapper;
import io.virtdata.threadstate.SharedState;

import java.util.function.Function;
import java.util.function.LongUnaryOperator;

@ThreadSafeMapper
@Categories({Category.state})
public class Save implements LongUnaryOperator {

    private final String name;
    private final Function<Object,Object> nameFunc;

    @Example({"Save('foo')","save the current long value to the name 'foo' in this thread"})
    public Save(String name) {
        this.name = name;
        this.nameFunc =null;
    }

    @Example({"Save(NumberNameToString())","save the current long value to the name generated by the function given."})
    public Save(Function<Object,Object> nameFunc) {
        this.name=null;
        this.nameFunc =nameFunc;
    }

    @Override
    public long applyAsLong(long operand) {
        String varname = (nameFunc !=null) ? String.valueOf(nameFunc.apply(operand)) : name;
        SharedState.tl_ObjectMap.get().put(varname,operand);
        return operand;
    }
}
