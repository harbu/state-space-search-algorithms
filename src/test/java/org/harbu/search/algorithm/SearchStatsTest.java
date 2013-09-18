package org.harbu.search.algorithm;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SearchStatsTest {

    private SearchStats stats;

    @Before
    public void setUp() {
        stats = new SearchStats("myAlgorithm", "myProblem");
    }

    @Test
    public void testNodeExpansionCounter_startsAtZero() {
        assertEquals(0, stats.getNodesExpanded());
    }

    @Test
    public void testNodeExpansionCounter_increment() {
        stats.nodeExpanded();
        stats.nodeExpanded();
        assertEquals(2, stats.getNodesExpanded());
    }

    @Test
    public void testNodesGeneratedCounter_startsAtZero() {
        assertEquals(0, stats.getNodesGenerated());
    }

    @Test
    public void testNodesGeneratedCounter_increment() {
        stats.nodeGenerated();
        stats.nodeGenerated();
        stats.nodeGenerated();

        assertEquals(3, stats.getNodesGenerated());
    }

    @Test
    public void testToString() {
        stats.nodeExpanded();
        stats.nodeGenerated();
        stats.nodeGenerated();
        stats.nodeGenerated();
        stats.nodeGenerated();

        String expected = "----- myAlgorithm @ myProblem -----\n"
                + "Nodes generated: 4\n"
                + "Nodes expanded:  1\n";

        assertEquals(expected, stats.toString());
    }
}