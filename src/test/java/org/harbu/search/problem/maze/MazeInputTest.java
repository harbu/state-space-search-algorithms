package org.harbu.search.problem.maze;

import org.harbu.search.util.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class MazeInputTest {
    
    private static final String VALID_INPUT =
              "############\n"
            + "# S ##    ##\n"
            + " #       #\n"
            + " # # #                G\n";
    
    private MazeInput mazeInput;

    @Before
    public void setUp() {
        mazeInput = new MazeInput(VALID_INPUT);
    }

    @Test(expected = MazeException.class)
    public void testConstruction_failMissingStart() {
        new MazeInput("### ####G");
    }

    @Test(expected = MazeException.class)
    public void testConstruction_failMissingGoal() {
        new MazeInput("###   S  ####");
    }

    @Test(expected = MazeException.class)
    public void testConstruction_invalidChar() {
        new MazeInput("#########\n"
                + "### S       G ####\n"
                + "ABBA");
    }

    @Test(expected = MazeException.class)
    public void testConstruction_multipleStarts() {
        new MazeInput("#### S G S ####");
    }

    @Test(expected = MazeException.class)
    public void testConstruction_multipleGoals() {
        new MazeInput("#### S  G # G ####");
    }

    @Test
    public void testGetLayout() {
        int[][] expected = new int[][]{
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        assertArrayEquals(expected, mazeInput.getLayout());
    }
    @Test
    public void testGetStartPosition() {
        assertEquals(new Coordinate(2, 1), mazeInput.getStartPosition());
    }

    @Test
    public void testGetGoalPosition() {
        assertEquals(new Coordinate(22, 3), mazeInput.getGoalPosition());
    }
}