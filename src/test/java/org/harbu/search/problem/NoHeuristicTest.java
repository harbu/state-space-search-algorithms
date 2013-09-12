package org.harbu.search.problem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class NoHeuristicTest {

    @Test
    public void testCalculate_alwaysReturnsZero() {
        assertEquals(0, new NoHeuristic<>().calculate(null), 0.00000001);
    }
}