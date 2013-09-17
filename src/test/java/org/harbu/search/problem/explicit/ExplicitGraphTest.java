package org.harbu.search.problem.explicit;

import org.harbu.search.problem.NoHeuristic;
import org.harbu.search.util.GraphNode;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExplicitGraphTest {

    @Test
    public void testVarsSet() {
        GraphNode start = new GraphNode("root");
        ExplicitGraph problem = new ExplicitGraph(start, start);
        assertEquals(new ExplicitGraphState(start), problem.getStartState());
        assertEquals(ExplicitGraphGoal.class, problem.getGoal().getClass());
        assertEquals(NoHeuristic.class, problem.getHeuristic().getClass());
    }
}