package org.harbu.search.problem.tsp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.harbu.search.problem.Operation;
import org.harbu.search.util.CompleteGraph;
import org.harbu.search.util.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

public class TSPStateTest {

    private static final String coordinateInput = "ant 12.5 8.0\n"
            + "dog 0.0 0.0\n"
            + "rabbit -52.7 63.21\n";
    private CompleteGraph graph;
    private TSPState state;

    @Before
    public void setUp() {
        graph = TSPCoordinateReader.readCoordinateString(coordinateInput);
        state = TSPState.makeTSPStart(graph, "ant");
    }

    @Test
    public void testToString() {
        assertEquals("ant", state.toString());
    }

    @Test
    public void testGetCurrentCity() {
        assertEquals("ant", state.getCurrentCity());
    }

    @Test
    public void testGetCitiesNotYetVisited() {
        Set<String> expected = new HashSet<>(Arrays.asList("dog", "rabbit"));
        assertEquals(expected, state.getCitiesNotYetVisited());
    }

    @Test
    public void testGetGraph() {
        assertSame(graph, state.getGraph());
    }

    @Test
    public void testIsReturnedHome_isFalseOnStart() {
        assertFalse(state.isReturnedHome());
        assertFalse(doSteps(state, 1).isReturnedHome());
        assertFalse(doSteps(state, 2).isReturnedHome());
    }

    @Test
    public void testIsReturnedHome_isFalseAfterFirstStep() {
        assertFalse(doSteps(state, 1).isReturnedHome());;
    }

    @Test
    public void testIsReturnedHome_isFalseAfterSecondStep() {
        assertFalse(doSteps(state, 2).isReturnedHome());
    }

    @Test
    public void testIsReturnedHome_isTrueOnFinalStep() {
        assertTrue(doSteps(state, 3).isReturnedHome());
    }

    @Test
    public void testGetOperations() {
        // TODO: BAD BAD, order is not clear...
        List<String> expectedLabels = Arrays.asList("rabbit", "dog");
        List<Double> expectedCosts = Arrays.asList(
                Coordinate.euclideanDistance(new Coordinate(12.5, 8), new Coordinate(-52.7, 63.21)),
                Coordinate.euclideanDistance(new Coordinate(12.5, 8), new Coordinate(0, 0)));

        List<Operation<TSPState>> operations = state.getOperations();

        assertEquals(expectedLabels.size(), operations.size());

        for (int i = 0; i < operations.size(); ++i) {
            assertEquals(expectedLabels.get(i), operations.get(i).getNode().toString());
            assertEquals(expectedCosts.get(i), operations.get(i).getCost(), 0.00001);
        }
    }

    @Test
    public void testEquals_false() {
        assertFalse(state.equals(doSteps(state, 1)));
    }

    @Test
    public void testEquals_true() {
        assertTrue(doSteps(state, 2).equals(doSteps(state, 2)));
    }

    @Test(expected = TSPException.class)
    public void testInvalidStartLabel() {
        TSPState.makeTSPStart(graph, "JASKLGJASKLG");
    }
}