package io.virtdata.discrete.int_int;

import io.virtdata.annotations.ThreadSafeMapper;
import org.apache.commons.statistics.distribution.HypergeometricDistribution;

@ThreadSafeMapper
public class Hypergeometrics extends IntToIntDiscreteCurve {
    public Hypergeometrics(int populationSize, int numberOfSuccesses, int sampleSize, String... modslist) {
        super(new HypergeometricDistribution(populationSize, numberOfSuccesses, sampleSize), modslist);
    }
}
