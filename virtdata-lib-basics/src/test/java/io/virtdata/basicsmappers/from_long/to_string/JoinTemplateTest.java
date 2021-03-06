package io.virtdata.basicsmappers.from_long.to_string;

import io.virtdata.basicsmappers.from_long.to_long.Mod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JoinTemplateTest {

    public void testBasicJoinTemplate() {
        JoinTemplate t1 = new JoinTemplate("__", new NumberNameToString(), new NumberNameToString());
        String v = t1.apply(3);
        assertThat(v).isEqualTo("three__four");
    }

    public void testPrefixSuffixJoinTemplate() {
        JoinTemplate t1 = new JoinTemplate("<","__", ">",new NumberNameToString(), new NumberNameToString());
        String v = t1.apply(3);
        assertThat(v).isEqualTo("<three__four>");
    }

    public void testIterOpFunctionJoinTemplate() {
        JoinTemplate t1 = new JoinTemplate(new Mod(5L), "<", "__",">",
                new NumberNameToString(), new NumberNameToString(), new NumberNameToString());
        String v = t1.apply(17);
        assertThat(v).isEqualTo("<two__three__four>");

    }

}