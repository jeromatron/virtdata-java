package io.virtdata.discrete.int_int;

import io.virtdata.annotations.ThreadSafeMapper;
import org.apache.commons.statistics.distribution.ZipfDistribution;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Zipf's_law">Wikipedia: Zipf's Law</a>
 * @see <a href="https://commons.apache.org/proper/commons-statistics/commons-statistics-distribution/apidocs/org/apache/commons/statistics/distribution/ZipfDistribution.html">Commons JavaDoc: ZipfDistribution</a>
 *
 * {@inheritDoc}
 */
@ThreadSafeMapper
public class Zipf extends IntToIntDiscreteCurve {
    public Zipf(int numberOfElements, double exponent, String... modslist) {
        super(new ZipfDistribution(numberOfElements, exponent), modslist);
    }
}
