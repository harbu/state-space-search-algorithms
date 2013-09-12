package org.harbu.search.problem.tsp;

import org.harbu.search.util.CompleteGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class TSPHeuristicTest {
    
    private static final String INPUT = "A 0 0\n"
            + "B 3 3\n"
            + "C 5 5\n"
            + "D 25 25\n";
    
    private TSPState state;
    
    @Before
    public void setUp() {
        CompleteGraph graph = TSPCoordinateReader.readCoordinateString(INPUT);
        state = TSPState.makeTSPStart(graph, INPUT);
    }

    /**
     * Test of calculate method, of class TSPHeuristic.
     */
    @Test
    public void testCalculate() {
    }
}