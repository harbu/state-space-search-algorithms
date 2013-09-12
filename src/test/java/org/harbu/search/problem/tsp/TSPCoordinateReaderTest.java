package org.harbu.search.problem.tsp;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.harbu.search.util.CompleteGraph;
import org.harbu.search.util.Edge;
import org.junit.Test;

public class TSPCoordinateReaderTest {

    @Test
    public void testReadCoordinateString() {
        String input = "A 0 0\n"
                + "B -5 5\n"
                + "C 1 1";

        List<String> expectedVertexLabels = Arrays.asList("A", "B", "C");

        Map<String, Set<Edge>> expectedEdges = new HashMap<>();

        expectedEdges.put("A", new HashSet<>(Arrays.asList(
                new Edge("A", "B", Math.sqrt(50)),
                new Edge("A", "C", Math.sqrt(2)))));

        expectedEdges.put("B", new HashSet<>(Arrays.asList(
                new Edge("B", "A", Math.sqrt(50)),
                new Edge("B", "C", Math.sqrt(52)))));


        expectedEdges.put("C", new HashSet<>(Arrays.asList(
                new Edge("C", "A", Math.sqrt(2)),
                new Edge("C", "B", Math.sqrt(52)))));


        CompleteGraph graph = TSPCoordinateReader.readCoordinateString(input);
        assertEquals(new HashSet<>(expectedVertexLabels), graph.getVertices());
        assertEquals(expectedEdges, graph.getEdges());
    }

    @Test(expected = TSPException.class)
    public void testInvalidFormat() {
        String input = "A 31.5 62.2\n"
                + "HELLOWORLD!!";
        TSPCoordinateReader.readCoordinateString(input);
    }
}