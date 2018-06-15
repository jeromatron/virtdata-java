package io.virtdata.templates;

import io.virtdata.api.ValuesBinder;
import io.virtdata.core.Bindings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringCompositor provides a way to build strings from a string template and provided values.
 *
 * <p>
 * The template is simply an array of string values, where odd indices represent token positions, and even indices represent
 * literals. This version of the StringCompositor fetches data from the bindings only for the named fields in the template.
 * </p>
 */
public class StringCompositor implements ValuesBinder<StringCompositor, String> {

//    private static Pattern tokenPattern = Pattern.compile("(?<!\\\\)\\{([^}]*)\\}(.*?)?",Pattern.DOTALL);
    private static Pattern tokenPattern = Pattern.compile("(?<section>(?<literal>[^{}]+)?(?<anchor>\\{(?<token>[a-zA-Z0-9-_.]+)?\\})?)");
    private String[] templateSegments;

    /**
     * Create a string template which has positional tokens, in "{}" form.
     * @param template The string template
     */
    public StringCompositor(String template) {
        templateSegments =parseTemplate(template);
    }

    /**
     * Parse the template according to the description for {@link StringCompositor}.
     *
     * @param template A string template.
     * @return A template array.
     */
    private String[] parseTemplate(String template) {
        Matcher matcher = tokenPattern.matcher(template);
        List<String> sections = new ArrayList<>();
        int counter=0;
        while (matcher.find()) {
            String literal = matcher.group("literal");
            String anchor = matcher.group("anchor");
            String token = matcher.group("token");
            if (anchor==null && literal==null) {
                break;
            }
            sections.add(Optional.ofNullable(literal).orElse(""));
            if (anchor!=null) {
                sections.add(Optional.ofNullable(token).orElse(String.valueOf(counter++)));
            }
        }
        if ((sections.size()%2)==0) {
            sections.add("");
        }
        return sections.toArray(new String[0]);
    }

    @Override
    public String bindValues(StringCompositor context, Bindings bindings, long cycle) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < templateSegments.length; i++) {
            if (i % 2 == 0) {
                sb.append(templateSegments[i]);
            } else {
                String key = templateSegments[i];
                Object value = bindings.get(key,cycle);
                sb.append(value.toString());
            }
        }
        return sb.toString();
    }

    public List<String> getBindPointNames() {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i <templateSegments.length; i++) {
            if (i%2==1) {
                tokens.add(templateSegments[i]);
            }
        }
        return tokens;
    }
}
