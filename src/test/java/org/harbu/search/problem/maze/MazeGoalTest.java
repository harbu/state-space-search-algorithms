package org.harbu.search.problem.maze;

import org.harbu.search.util.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

/**
 *
 * @author harbu
 */
public class MazeGoalTest {

    private MazeState state;
    private MazeGoal goal;

    @Before
    public void setUp() {
        state = new MazeState(new int[][]{{1, 0, 0, 0, 1}}, new Coordinate(3, 0));
        goal = new MazeGoal(new Coordinate(1, 0));
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