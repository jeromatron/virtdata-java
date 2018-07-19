package io.virtdata.discrete.int_int;

import io.virtdata.annotations.ThreadSafeMapper;
import org.apache.commons.statistics.distribution.PascalDistribution;

/**
 * @see <a href="https://commons.apache.org/proper/commons-statistics/commons-statistics-distribution/apidocs/org/apache/commons/statistics/distribution/PascalDistribution.html">Commons JavaDoc: PascalDistribution</a>
 * @see <a href="https://en.wikipedia.org/wiki/Negative_binomial_distribution">Wikipedia: Negative binomial distribution</a>
 *
 * {@inheritDoc}
 */
@ThreadSafeMapper
public class Pascal extends IntToIntDiscreteCurve {
    public Pascal(int r, double p, String... modslist) {
        super(new PascalDistribution(r, p), modslist);
    }
}
