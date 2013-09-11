package integrationtests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.AnyOf.*;
import static org.hamcrest.core.Is.*;
import org.harbu.search.algorithm.AStar;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.BruteForceSearch;
import static org.harbu.search.algorithm.BruteForceSearch.SearchType.BREADTH_FIRST;
import static org.harbu.search.algorithm.BruteForceSearch.SearchType.DEPTH_FIRST;
import org.harbu.search.algorithm.IDAStar;
import org.harbu.search.problem.constraint.ConstraintSatisfaction;
import org.harbu.search.problem.constraint.ConstraintSatisfactionState;
import org.harbu.search.problem.constraint.impl.NQueensState;
import org.harbu.search.problem.constraint.impl.SudokuState;
import org.harbu.search.problem.slidingpuzzle.NPuzzle;
import org.harbu.search.problem.slidingpuzzle.NPuzzleState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class ConstraintSatisfactionIT {

    private static final int[][] SUDOKU_INPUT = new int[][]{
        {3, 2, 1, 7, 0, 4, 0, 0, 0},
        {6, 4, 0, 0, 9, 0, 0, 0, 7},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 9, 0, 0},
        {0, 0, 5, 1, 8, 7, 4, 0, 0},
        {0, 0, 4, 9, 6, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 0, 0, 0, 7, 0, 0, 1, 9},
        {0, 0, 0, 6, 0, 9, 5, 8, 2}
    };
    
    private static final String SUDOKU_EXPECTED_RESULT =
              "|3 2 1|7 5 4|6 9 8|\n"
            + "|6 4 8|2 9 3|1 5 7|\n"
            + "|5 7 9|8 1 6|2 3 4|\n"
            + "\n"
            + "|7 8 2|3 4 5|9 6 1|\n"
            + "|9 6 5|1 8 7|4 2 3|\n"
            + "|1 3 4|9 6 2|8 7 5|\n"
            + "\n"
            + "|8 9 3|5 2 1|7 4 6|\n"
            + "|2 5 6|4 7 8|3 1 9|\n"
            + "|4 1 7|6 3 9|5 8 2|\n";
    private static final List<String> FOUR_QUEENS_SOLUTIONS = Arrays.asList(
              "XXQX\n"
            + "QXXX\n"
            + "XXXQ\n"
            + "XQXX\n",
            
              "XQXX\n"
            + "XXXQ\n"
            + "QXXX\n"
            + "XXQX\n");
    
    ConstraintSatisfaction fourQueensProblem;
    ConstraintSatisfaction sudokuProblem;

    @Before
    public void setUp() {
        fourQueensProblem = new ConstraintSatisfaction(new NQueensState(4));
        sudokuProblem = new ConstraintSatisfaction(new SudokuState(SUDOKU_INPUT));
    }
    
    @Test
    public void testAlgorithm_queensProblem() {
        for (Algorithm algorithm : setUpAlgorithms(fourQueensProblem)) {
            assertTrue(algorithm.solve());
            assertThat(algorithm.getPathToGoal().getLast().toString(),
                    anyOf(is(FOUR_QUEENS_SOLUTIONS.get(0)),
                    is(FOUR_QUEENS_SOLUTIONS.get(1))));
        }
    }
    
    @Test
    public void testAlgorithm_sudokuProblem() {
        for (Algorithm algorithm : setUpAlgorithms(sudokuProblem)) {
            assertTrue(algorithm.solve());
            assertEquals(SUDOKU_EXPECTED_RESULT, algorithm.getPathToGoal().getLast().toString());
        }
    }

    private List<Algorithm<ConstraintSatisfactionState>> setUpAlgorithms(ConstraintSatisfaction problem) {
        List<Algorithm<ConstraintSatisfactionState>> algorithmsToRun = new ArrayList<>();
        algorithmsToRun.add(new AStar<>(problem));
        algorithmsToRun.add(new IDAStar<>(problem));
        algorithmsToRun.add(new BruteForceSearch<>(problem, DEPTH_FIRST));
        algorithmsToRun.add(new BruteForceSearch<>(problem, BREADTH_FIRST));
        return algorithmsToRun;
    }
}