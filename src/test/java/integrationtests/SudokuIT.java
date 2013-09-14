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
import org.harbu.search.problem.constraint.ConstraintSatisfaction;
import org.harbu.search.problem.constraint.ConstraintSatisfactionState;
import org.harbu.search.problem.constraint.impl.SudokuState;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class SudokuIT {

    private static final int[][][] INPUT = new int[][][]{
        {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9},},
        {
            {3, 0, 9, 0, 0, 0, 4, 0, 0},
            {2, 0, 0, 7, 0, 9, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 0, 0},
            {7, 5, 0, 0, 6, 0, 2, 3, 0},
            {6, 0, 0, 9, 0, 4, 0, 0, 8},
            {0, 2, 8, 0, 5, 0, 0, 4, 1},
            {0, 0, 0, 0, 0, 0, 5, 9, 0},
            {0, 0, 0, 1, 0, 6, 0, 0, 7},
            {0, 0, 6, 0, 0, 0, 1, 0, 4}
        }
    };
    private static final String EXPECTED_RESULT_1 =
            "|5 3 4|6 7 8|9 1 2|\n"
            + "|6 7 2|1 9 5|3 4 8|\n"
            + "|1 9 8|3 4 2|5 6 7|\n"
            + "\n"
            + "|8 5 9|7 6 1|4 2 3|\n"
            + "|4 2 6|8 5 3|7 9 1|\n"
            + "|7 1 3|9 2 4|8 5 6|\n"
            + "\n"
            + "|9 6 1|5 3 7|2 8 4|\n"
            + "|2 8 7|4 1 9|6 3 5|\n"
            + "|3 4 5|2 8 6|1 7 9|\n";
    private static final String EXPECTED_RESULT_2 =
            "|3 6 9|2 1 8|4 7 5|\n"
            + "|2 1 5|7 4 9|8 6 3|\n"
            + "|4 8 7|6 3 5|9 1 2|\n"
            + "\n"
            + "|7 5 4|8 6 1|2 3 9|\n"
            + "|6 3 1|9 2 4|7 5 8|\n"
            + "|9 2 8|3 5 7|6 4 1|\n"
            + "\n"
            + "|1 7 3|4 8 2|5 9 6|\n"
            + "|5 4 2|1 9 6|3 8 7|\n"
            + "|8 9 6|5 7 3|1 2 4|\n";
    private ConstraintSatisfaction problem;
    private List<Algorithm<ConstraintSatisfactionState>> algorithmsToRun;

    @Test
    public void testAlgorithms1() {
        problem = new ConstraintSatisfaction(new SudokuState(INPUT[0]));
        setUpAlgorithms(problem);
        for (Algorithm<ConstraintSatisfactionState> algorithm : algorithmsToRun) {
            Result<ConstraintSatisfactionState> result = algorithm.solve();
            assertTrue(result.foundSolution());
            assertEquals(EXPECTED_RESULT_1, result.getGoalState().toString());
        }
    }

    @Test
    public void testAlgorithms2() {
        problem = new ConstraintSatisfaction(new SudokuState(INPUT[1]));
        setUpAlgorithms(problem);
        for (Algorithm<ConstraintSatisfactionState> algorithm : algorithmsToRun) {
            Result<ConstraintSatisfactionState> result = algorithm.solve();
            assertTrue(result.foundSolution());
            assertEquals(EXPECTED_RESULT_2, result.getGoalState().toString());
        }
    }

    private void setUpAlgorithms(ConstraintSatisfaction problem) {
        algorithmsToRun = new ArrayList<>();
        algorithmsToRun.add(new AStar<>(problem));
        algorithmsToRun.add(new IDAStar<>(problem));
        algorithmsToRun.add(new DFBnB<>(problem));
        algorithmsToRun.add(new BruteForceSearch<>(problem, DEPTH_FIRST));
        algorithmsToRun.add(new BruteForceSearch<>(problem, BREADTH_FIRST));
    }
}
