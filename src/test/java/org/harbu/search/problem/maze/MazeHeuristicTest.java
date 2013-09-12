package org.harbu.search.problem.maze;

import org.harbu.search.util.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeHeuristicTest {

    @Test
    public void testCalculate() {
        MazeHeuristic heuristic = new MazeHeuristic(Coordinate.ORIGIN);
        MazeState state = new MazeState(new int[][]{
            {0, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 0, 0}
        }, new Coordinate(2, 3));

        assertEquals(2 + 3, (int) heuristic.calculate(state));
    }
}