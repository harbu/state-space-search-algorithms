package org.harbu.search.problem.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class MazeTest {

    @Test
    public void testVarsSet() {
        Maze problem = new Maze(new MazeInput("## S  G ###"));
        assertEquals(MazeState.class, problem.getStartState().getClass());
        assertEquals(MazeGoal.class, problem.getGoal().getClass());
        assertEquals(MazeHeuristic.class, problem.getHeuristic().getClass());
    }
}