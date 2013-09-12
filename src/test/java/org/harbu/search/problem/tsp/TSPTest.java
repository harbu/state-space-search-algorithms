package org.harbu.search.problem.tsp;

import java.util.Arrays;
import org.harbu.search.util.CompleteGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TSPTest {

    private TSPState state;
    private TSP problem;

    @Before
    public void setUp() {
        CompleteGraph graph = new CompleteGraph(Arrays.asList("A"), new double[0][0]);
        state = TSPState.makeTSPStart(graph, "A");
        problem = new TSP(state);
    }

    @Test
    public void testVarsSet() {
        assertSame(state, problem.getStartState());
        assertEquals(TSPGoal.class, problem.getGoal().getClass());
        assertEquals(TSPHeuristic.class, problem.getHeuristic().getClass());
    }
}