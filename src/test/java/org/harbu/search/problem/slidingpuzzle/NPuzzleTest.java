package org.harbu.search.problem.slidingpuzzle;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class NPuzzleTest {

    private NPuzzleState start;
    private NPuzzleState goal;
    private NPuzzle problem;

    @Before
    public void setUp() {
        start = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        });
        goal = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        });
        problem = new NPuzzle(start, goal);
    }

    @Test
    public void testVarsSet() {
        assertSame(start, problem.getStartState());
        assertEquals(NPuzzleGoal.class, problem.getGoal().getClass());
        assertEquals(NPuzzleHeuristic.class, problem.getHeuristic().getClass());
    }
}