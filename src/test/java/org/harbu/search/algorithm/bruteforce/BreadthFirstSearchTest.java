package org.harbu.search.algorithm.bruteforce;

import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.Result;
import org.harbu.search.problem.explicit.ExplicitGraph;
import org.harbu.search.problem.explicit.ExplicitGraphState;
import org.harbu.search.util.GraphNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Eric Andrews
 */
public class BreadthFirstSearchTest extends BruteForceSearchTest {

    @Test
    public void testFindTrivial() {
        Result<ExplicitGraphState> result = runBFSWithGoals(root);

        assertTrue(result.foundSolution());
        assertEquals(new ExplicitGraphState(root), result.getGoalState());
        assertEquals(1, result.getStats().getNodesExpanded());
        assertEquals(0, result.getStats().getNodesGenerated());
    }

    @Test
    public void testFindsShallowGoal() {
        Result<ExplicitGraphState> result = runBFSWithGoals(c, aaaa);

        assertTrue(result.foundSolution());
        assertEquals(new ExplicitGraphState(c), result.getGoalState());
        assertEquals(4, result.getStats().getNodesExpanded());
        assertEquals(3 + 2 + 3 + 0, result.getStats().getNodesGenerated());
    }

    public void testFindAAA() {
        Result<ExplicitGraphState> result = runBFSWithGoals(aaaa);

        assertTrue(result.foundSolution());
        assertEquals(new ExplicitGraphState(aaaa), result.getGoalState());
        assertEquals(14, result.getStats().getNodesExpanded());
        assertEquals(13, result.getStats().getNodesExpanded());
    }

    private Result<ExplicitGraphState> runBFSWithGoals(GraphNode... goals) {
        ExplicitGraph problem = new ExplicitGraph(root, goals);
        Algorithm<ExplicitGraphState> algorithm = new BreadthFirstSearch<>(problem);
        return algorithm.run();
    }
}
