package io.virtdata.core;

import io.virtdata.api.DataMapper;
import io.virtdata.api.VirtDataFunctionLibrary;
import io.virtdata.ast.VirtDataFlow;
import io.virtdata.parser.VirtDataDSL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VirtDataLibrary {

    VirtDataFunctionLibrary getFunctionLibrary();
    String getLibname();

    default BindingsTemplate getBindingsTemplate(String... namesAndSpecs) {
        if ((namesAndSpecs.length%2)!=0) {
            throw new RuntimeException(
                    "args must be in 'name','spec', pairs. " +
                            "This can't be true for " + namesAndSpecs.length + "elements.");
        }
        Map<String,String> specmap = new HashMap<>();
        for (int i = 0; i < namesAndSpecs.length; i+=2) {
            specmap.put(namesAndSpecs[i],namesAndSpecs[i+1]);
        }
        return getBindingsTemplate(specmap);
    }

    default <T> Optional<DataMapper<T>> getDataMapper(String flowSpec) {

        VirtDataDSL.ParseResult parseResult = VirtDataDSL.parse(flowSpec);
        if (parseResult.throwable!=null) {
            throw new RuntimeException(parseResult.throwable);
        }
        VirtDataFlow flow = parseResult.flow;
        VirtDataComposer composer = new VirtDataComposer();
        Optional<ResolvedFunction> resolvedFunction = composer.resolveFunctionFlow(flow);
        return resolvedFunction.map(ResolvedFunction::getFunctionObject).map(DataMapperFunctionMapper::map);
    }

    /**
     * Create a bindings template from the provided map, ensuring that
     * the syntax of the bindings specs is parsable first.
     * @param namedBindings The named bindings map
     * @return a bindings template
     */
    default BindingsTemplate getBindingsTemplate(Map<String, String> namedBindings) {

        for(String bindingSpec : namedBindings.values()) {
            VirtDataDSL.ParseResult parseResult = VirtDataDSL.parse(bindingSpec);
            if (parseResult.throwable!=null) {
                throw new RuntimeException(parseResult.throwable);
            }
        }
        return new BindingsTemplate(this, namedBindings);
    }

    default <T> DataMapper<T> getDataMapper(String s, Class<? extends T> clazz) {
        Optional<DataMapper<T>> dataMapper = getDataMapper(s);
        return dataMapper.orElseThrow(() -> new RuntimeException("Unable to find mapper: " + s));
    }


    default List<String> getDataMapperNames() {
        return getFunctionLibrary().getDataMapperNames();
    }
}