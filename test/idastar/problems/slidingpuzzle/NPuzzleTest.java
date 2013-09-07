package idastar.problems.slidingpuzzle;

import idastar.problems.Move;
import org.junit.*;
import static org.junit.Assert.*;

import idastar.problems.slidingpuzzle.NPuzzle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NPuzzleTest {

    private NPuzzle eightPuzzle;

    @Before
    public void setUp() {
        eightPuzzle = NPuzzle.createEightPuzzle(new int[][]{
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
        assertEquals(NPuzzle.EMPTY_SPACE, eightPuzzle.getNumberAtCoordinate(0, 2));
    }

    @Test
    public void testEquals_notEqual_differentSize() {
        NPuzzle fifteenPuzzle = NPuzzle.createFifteenPuzzle(new int[][]{
            {1, 3, 2, 9},
            {4, 7, 5, 10},
            {0, 6, 8, 11},
            {12, 13, 14, 15}
        });
        assertFalse(eightPuzzle.equals(fifteenPuzzle));
    }

    @Test
    public void testEquals_equal_other() {
        NPuzzle other = NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 7, 5},
            {0, 6, 8}
        });
        assertTrue(eightPuzzle.equals(other));
    }

    @Test
    public void testGetMoves_emptySpaceInCorner() {
        Move<NPuzzle> optionOne = new Move<>(NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 7, 5},
            {6, 0, 8}
        }), NPuzzle.COST_TO_MOVE);

        Move<NPuzzle> optionTwo = new Move<>(NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {0, 7, 5},
            {4, 6, 8}
        }), NPuzzle.COST_TO_MOVE);

        List<Move<NPuzzle>> expected = Arrays.asList(optionOne, optionTwo);
        assertEquals(new HashSet<>(expected),
                new HashSet<>(eightPuzzle.getMoves()));
    }

    @Test
    public void testGetMoves_emptySpaceInMiddle() {
        eightPuzzle = NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 0, 5},
            {7, 6, 8}
        });

        Move<NPuzzle> optionOne = new Move<>(NPuzzle.createEightPuzzle(new int[][]{
            {1, 0, 2},
            {4, 3, 5},
            {7, 6, 8}
        }), NPuzzle.COST_TO_MOVE);

        Move<NPuzzle> optionTwo = new Move<>(NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 5, 0},
            {7, 6, 8}
        }), NPuzzle.COST_TO_MOVE);

        Move<NPuzzle> optionThree = new Move<>(NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {4, 6, 5},
            {7, 0, 8}
        }), NPuzzle.COST_TO_MOVE);

        Move<NPuzzle> optionFour = new Move<>(NPuzzle.createEightPuzzle(new int[][]{
            {1, 3, 2},
            {0, 4, 5},
            {7, 6, 8}
        }), NPuzzle.COST_TO_MOVE);


        List<Move<NPuzzle>> expected =
                Arrays.asList(optionOne, optionTwo, optionThree, optionFour);
        assertEquals(new HashSet<>(expected),
                new HashSet<>(eightPuzzle.getMoves()));
    }
}