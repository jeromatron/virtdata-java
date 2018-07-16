package io.virtdata.continuous.long_double;

import io.virtdata.annotations.ThreadSafeMapper;
import org.apache.commons.statistics.distribution.UniformContinuousDistribution;

/**
 * {@inheritDoc}
 *
 * @see io.virtdata.continuous.long_double.LongToDoubleContinuousCurve
 */
@ThreadSafeMapper
public class UniformContinous extends LongToDoubleContinuousCurve {
    public UniformContinous(double lower, double upper, String... mods) {
        super(new UniformContinuousDistribution(lower, upper), mods);
    }
}