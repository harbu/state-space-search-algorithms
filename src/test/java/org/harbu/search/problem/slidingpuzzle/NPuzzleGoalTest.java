package org.harbu.search.problem.slidingpuzzle;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class NPuzzleGoalTest {

    private NPuzzleGoal goal;
    private NPuzzleState state;

    @Before
    public void setUp() {
        state = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        });
        goal = new NPuzzleGoal(state);
    }

    @Test
    public void testIsGoalReached_false() {
        NPuzzleState myState = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 2, 3},
            {4, 5, 8},
            {7, 0, 6}
        });
        assertFalse(goal.isGoalReached(myState));
    }

    @Test
    public void testIsGoalReached_true() {
        NPuzzleState myState = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        });
        assertTrue(goal.isGoalReached(myState));
    }
}