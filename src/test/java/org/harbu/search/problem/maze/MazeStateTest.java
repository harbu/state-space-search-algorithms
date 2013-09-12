package org.harbu.search.problem.maze;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.harbu.search.problem.Operation;
import org.harbu.search.util.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.harbu.search.test.TestHelper.*;

public class MazeStateTest {

    private final String INPUT =
            "#  ##\n"
            + "# S##\n"
            + "# #G#\n"
            + "# # #\n"
            + "#   #\n";
    private MazeState state;

    @Before
    public void setUp() {
        state = new Maze(new MazeInput(INPUT)).getStartState();
    }

    @Test
    public void testToString() {
        String expected = INPUT.replace("G", " ");
        assertEquals(expected, state.toString());
    }

    @Test
    public void testGetPosition() {
        Coordinate coord1 = new Coordinate(2, 1).up();
        Coordinate coord2 = new Coordinate(2, 1).left();

        Coordinate actual = doSteps(state, 1).getPosition();
        assertThat(actual, anyOf(equalTo(coord1), equalTo(coord2)));
    }

    @Test
    public void testGetOperations() {
        Set<String> expectedStates = new HashSet<>(Arrays.asList(
                "# S##\n"
                + "#  ##\n"
                + "# # #\n"
                + "# # #\n"
                + "#   #\n",
                "#  ##\n"
                + "#S ##\n"
                + "# # #\n"
                + "# # #\n"
                + "#   #\n"));

        List<Operation<MazeState>> operations = state.getOperations();

        Set<String> actualStates = new HashSet<>();

        for (Operation<MazeState> operation : operations) {
            actualStates.add(operation.getNode().toString());
            assertEquals(1, operation.getCost(), 0.0000001);
        }

        assertEquals(expectedStates, actualStates);
    }

    @Test
    public void testEquals_false() {
        assertFalse(state.equals(doSteps(state, 1)));
    }

    @Test
    public void testEquals_true() {
        assertTrue(doSteps(state, 2).equals(doSteps(state, 2)));
    }
}