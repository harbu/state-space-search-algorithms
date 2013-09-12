package org.harbu.search.problem.tsp;

import org.harbu.search.util.CompleteGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

/**
 *
 * @author harbu
 */
public class TSPGoalTest {
    
    private static final String INPUT = "A 0 0\nB 0 1\n";
    
    private TSPGoal goal = new TSPGoal();
    private TSPState state;
    
    @Before
    public void setUp() {
        CompleteGraph graph = TSPCoordinateReader.readCoordinateString(INPUT);
        state = TSPState.makeTSPStart(graph, "B");
    }

    @Test
    public void testIsGoalReached_false() {
        assertFalse(goal.isGoalReached(state));
    }
    
    @Test
    public void testIsGoalReached_true() {
        assertTrue(goal.isGoalReached(doSteps(state, 2)));
    }
}