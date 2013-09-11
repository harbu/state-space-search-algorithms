package org.harbu.search.util;

import org.harbu.search.util.CompleteGraph;
import org.harbu.search.util.Edge;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CompleteGraphTest {

    private CompleteGraph graph;

    @Before
    public void setUp() {
        List<String> vertexLabels = Arrays.asList("Joulu", "Ammatti", "Ovi", "Kalakukko");
        double[][] lowerHalfCostMatrix = new double[][]{
            {},
            {5},
            {3, 7},
            {1, 4, 13}
        };
        graph = new CompleteGraph(vertexLabels, lowerHalfCostMatrix);
    }

    @Test
    public void testCost() {
        assertEquals(13, (int) graph.cost("Kalakukko", "Ovi"));
        assertEquals(13, (int) graph.cost("Ovi", "Kalakukko"));
        assertEquals(0, (int) graph.cost("Ovi", "Ovi"));
    }

    @Test
    public void testGetVertices() {
        List<String> expected = Arrays.asList("Joulu", "Ammatti", "Ovi", "Kalakukko");
        assertEquals(new HashSet<>(expected), graph.getVertices());
    }

    @Test
    public void testGetEdges() {
        Map<String, Set<Edge>> expected = new HashMap<>();

        expected.put("Joulu", new HashSet(Arrays.asList(
                new Edge("Joulu", "Ammatti", 5),
                new Edge("Joulu", "Ovi", 3),
                new Edge("Joulu", "Kalakukko", 1))));

        expected.put("Ammatti", new HashSet(Arrays.asList(
                new Edge("Ammatti", "Joulu", 5),
                new Edge("Ammatti", "Ovi", 7),
                new Edge("Ammatti", "Kalakukko", 4))));

        expected.put("Ovi", new HashSet(Arrays.asList(
                new Edge("Ovi", "Joulu", 3),
                new Edge("Ovi", "Ammatti", 7),
                new Edge("Ovi", "Kalakukko", 13))));

        expected.put("Kalakukko", new HashSet(Arrays.asList(
                new Edge("Kalakukko", "Joulu", 1),
                new Edge("Kalakukko", "Ammatti", 4),
                new Edge("Kalakukko", "Ovi", 13))));

        assertEquals(expected, graph.getEdges());
    }

    @Test
    public void testToString() {
        String expected = ""
                + "Joulu     [0.0, 5.0, 3.0, 1.0]\n"
                + "Ammatti   [5.0, 0.0, 7.0, 4.0]\n"
                + "Ovi       [3.0, 7.0, 0.0, 13.0]\n"
                + "Kalakukko [1.0, 4.0, 13.0, 0.0]\n";
        assertEquals(expected, graph.toString());
    }

    @Test
    public void testSubgraph() {
        CompleteGraph subGraph = graph.subGraph(Arrays.asList("Joulu", "Kalakukko", "Ovi"));
        String expected = ""
                + "Joulu     [0.0, 1.0, 3.0]\n"
                + "Kalakukko [1.0, 0.0, 13.0]\n"
                + "Ovi       [3.0, 13.0, 0.0]\n";
        assertEquals(expected, subGraph.toString());
    }
}