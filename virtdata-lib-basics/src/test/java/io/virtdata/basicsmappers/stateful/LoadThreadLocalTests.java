package io.virtdata.basicsmappers.stateful;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadThreadLocalTests {

    @Test(singleThreaded = true)
    public void testSaveLoadDouble() {
        new Clear().applyAsLong(0L);
        SaveDouble saver = new SaveDouble("doublename");
        LoadDouble loader = new LoadDouble("doublename");
        double passiveOutput = saver.applyAsDouble(123L);
        double loadedValue = loader.apply(456L);
        assertThat(passiveOutput).isEqualTo(123L);
        assertThat(loadedValue).isEqualTo(123L);
    }

    @Test(singleThreaded = true)
    public void testSaveLoadInteger() {
        new Clear().applyAsLong(0L);
        SaveInteger saver = new SaveInteger("intname");
        LoadInteger loader = new LoadInteger("intname");
        int passiveOutput = saver.applyAsInt(123);
        int loadedValue = loader.apply(456);
        assertThat(passiveOutput).isEqualTo(123);
        assertThat(loadedValue).isEqualTo(123);
    }

    @Test(singleThreaded = true)
    public void testSaveLoadFloat() {
        new Clear().applyAsLong(0L);
        SaveFloat saver = new SaveFloat("floatname");
        LoadFloat loader = new LoadFloat("floatname");
        float passiveOutput = saver.apply(123f);
        float loadedValue = loader.apply(456f);
        assertThat(passiveOutput).isEqualTo(123f);
        assertThat(loadedValue).isEqualTo(123f);
    }

    @Test(singleThreaded = true)
    public void testSaveLoadLong() {
        new Clear().applyAsLong(0L);
        SaveLong saver = new SaveLong("longname");
        LoadLong loader = new LoadLong("longname");
        long passiveOutput = saver.applyAsLong(123L);
        long loadedValue = loader.apply(456L);
        assertThat(passiveOutput).isEqualTo(123L);
        assertThat(loadedValue).isEqualTo(123L);
    }

    @Test(singleThreaded = true)
    public void testSaveLoadString() {
        new Clear().applyAsLong(0L);
        SaveString saver = new SaveString("longname");
        LoadString loader = new LoadString("longname");
        String passiveOutput = saver.apply("stringval");
        String loadedValue = loader.apply(456L);
        assertThat(passiveOutput).isEqualTo("stringval");
        assertThat(loadedValue).isEqualTo("stringval");
    }

}