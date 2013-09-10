package harbu.search.problem.slidingpuzzle;

import harbu.search.problem.slidingpuzzle.NPuzzleState;
import harbu.search.problem.Operation;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NPuzzleTest {

    private NPuzzleState eightPuzzle;

    @Before
    public void setUp() {
        eightPuzzle = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 7, 5},
            {0, 6, 8}
        });
    }

    @Test
    public void testToString() {
        String expected =
                "  1  3  2\n"
                + "  4  7  5\n"
                + "  _  6  8\n";
        assertEquals(expected, eightPuzzle.toString());
    }

    @Test
    public void testGetNumberAtCoordinate() {
        assertEquals(2, eightPuzzle.getNumberAtCoordinate(2, 0));
        assertEquals(NPuzzleState.EMPTY_SPACE, eightPuzzle.getNumberAtCoordinate(0, 2));
    }

    @Test
    public void testEquals_notEqual_differentSize() {
        NPuzzleState fifteenPuzzle = NPuzzleState.createFifteenPuzzle(new int[][]{
            {1, 3, 2, 9},
            {4, 7, 5, 10},
            {0, 6, 8, 11},
            {12, 13, 14, 15}
        });
        assertFalse(eightPuzzle.equals(fifteenPuzzle));
    }

    @Test
    public void testEquals_equal_other() {
        NPuzzleState other = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 7, 5},
            {0, 6, 8}
        });
        assertTrue(eightPuzzle.equals(other));
    }

    @Test
    public void testGetMoves_emptySpaceInCorner() {
        Operation<NPuzzleState> optionOne = new Operation<>(NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 7, 5},
            {6, 0, 8}
        }), NPuzzleState.COST_TO_MOVE);

        Operation<NPuzzleState> optionTwo = new Operation<>(NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {0, 7, 5},
            {4, 6, 8}
        }), NPuzzleState.COST_TO_MOVE);

        List<Operation<NPuzzleState>> expected = Arrays.asList(optionOne, optionTwo);
        assertEquals(new HashSet<>(expected),
                new HashSet<>(eightPuzzle.getOperations()));
    }

    @Test
    public void testGetMoves_emptySpaceInMiddle() {
        eightPuzzle = NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 0, 5},
            {7, 6, 8}
        });

        Operation<NPuzzleState> optionOne = new Operation<>(NPuzzleState.createEightPuzzle(new int[][]{
            {1, 0, 2},
            {4, 3, 5},
            {7, 6, 8}
        }), NPuzzleState.COST_TO_MOVE);

        Operation<NPuzzleState> optionTwo = new Operation<>(NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 5, 0},
            {7, 6, 8}
        }), NPuzzleState.COST_TO_MOVE);

        Operation<NPuzzleState> optionThree = new Operation<>(NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 6, 5},
            {7, 0, 8}
        }), NPuzzleState.COST_TO_MOVE);

        Operation<NPuzzleState> optionFour = new Operation<>(NPuzzleState.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {0, 4, 5},
            {7, 6, 8}
        }), NPuzzleState.COST_TO_MOVE);


        List<Operation<NPuzzleState>> expected =
                Arrays.asList(optionOne, optionTwo, optionThree, optionFour);
        assertEquals(new HashSet<>(expected),
                new HashSet<>(eightPuzzle.getOperations()));
    }
}