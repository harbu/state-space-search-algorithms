package integrationtests;

import static org.harbu.search.algorithm.BruteForceSearch.SearchType.*;
import java.util.ArrayList;
import java.util.List;
import org.harbu.search.algorithm.AStar;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.BruteForceSearch;
import org.harbu.search.algorithm.DFBnB;
import org.harbu.search.algorithm.IDAStar;
import org.harbu.search.algorithm.Result;
import org.harbu.search.problem.slidingpuzzle.NPuzzle;
import org.harbu.search.problem.slidingpuzzle.NPuzzleState;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author harbu
 */
public class NPuzzleIT {

    private static final int[][][] INPUT = new int[][][]{
        {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
        },
        {
            {1, 5, 3},
            {2, 6, 8},
            {7, 0, 4}
        },
        {
            {8, 2, 5},
            {4, 7, 0},
            {1, 3, 6}
        },
        {
            {6, 5, 7},
            {0, 4, 3},
            {1, 2, 8}
        }
    };
    private static final NPuzzleState GOAL = NPuzzleState.createEightPuzzle(new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    });
    private List<NPuzzle> problems;

    @Before
    public void setUp() {
        problems = new ArrayList<>();
        for (int[][] layout : INPUT) {
            NPuzzleState start = NPuzzleState.createEightPuzzle(layout);
            problems.add(new NPuzzle(start, GOAL));
        }
    }

    @Test
    public void testAlgorithms() {
        for (NPuzzle problem : problems) {
            for (Algorithm<NPuzzleState> algorithm : setUpAlgorithms(problem)) {
                Result<NPuzzleState> result = algorithm.run();
                assertTrue(result.foundSolution());
                assertEquals(GOAL, result.getGoalState());
            }
        }
    }

    private List<Algorithm<NPuzzleState>> setUpAlgorithms(NPuzzle problem) {
        List<Algorithm<NPuzzleState>> algorithmsToRun = new ArrayList<>();
        algorithmsToRun.add(new AStar<>(problem));
        algorithmsToRun.add(new IDAStar<>(problem));
        algorithmsToRun.add(new BruteForceSearch<>(problem, DEPTH_FIRST));
        algorithmsToRun.add(new BruteForceSearch<>(problem, BREADTH_FIRST));
        return algorithmsToRun;
    }
}
