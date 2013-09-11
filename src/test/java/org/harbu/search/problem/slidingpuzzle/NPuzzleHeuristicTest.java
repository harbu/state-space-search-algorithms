package org.harbu.search.problem.slidingpuzzle;

import org.junit.*;
import static org.junit.Assert.*;

public class NPuzzleHeuristicTest {

    private NPuzzleState goal;
    private NPuzzleHeuristic heuristic;

    @Before
    public void setUp() {
        goal = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        });
        heuristic = new NPuzzleHeuristic(goal);
    }

    @Test
    public void testCalculate_one() {
        assertEquals(0, heuristic.calculate(goal));
    }

    @Test
    public void testCalculate_two() {
        NPuzzleState node = NPuzzleState.createEightPuzzle(new int[][]{
            {8, 5, 3},
            {2, 0, 7},
            {4, 6, 1}
        });

        // distance of 1 to goal position, distance of 2 to goal position,
        // ...,  distance of 9 to goal position
        int expected = 4 + 2 + 0 + 1 + 1 + 2 + 3 + 3;
        assertEquals(expected, heuristic.calculate(node));
    }

    @Test(expected = NPuzzleException.class)
    public void testCalculate_incompatibleSize() {
        NPuzzleState node = NPuzzleState.createFifteenPuzzle(new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        });
        heuristic.calculate(node);
    }
}