package org.harbu.search.problem.tsp;

import org.harbu.search.util.CompleteGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

/**
 *
 * @author harbu
 */
public class TSPHeuristicTest {

    private static final String INPUT = "A 0 0\n"
            + "B 1 1\n"
            + "C 2 2\n"
            + "D 3 3\n";
    
    private TSPHeuristic heuristic = new TSPHeuristic();
    private TSPState state;

    @Before
    public void setUp() {
        CompleteGraph graph = TSPCoordinateReader.readCoordinateString(INPUT);
        state = TSPState.makeTSPStart(graph, "A");
    }

    @Test
    public void testCalculate() {
        double expected = Math.sqrt(2) + Math.sqrt(2) + Math.sqrt(2);
        
        assertEquals(expected, heuristic.calculate(state), 0.00001);
    }
    
    @Test
    public void testCalculate_atEnd() {
        assertEquals(0, (int) heuristic.calculate(doSteps(state, 4)));
    }
}