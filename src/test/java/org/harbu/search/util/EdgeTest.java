package org.harbu.search.util;

import org.harbu.search.util.Edge;
import org.junit.Test;
import static org.junit.Assert.*;


public class EdgeTest {

    @Test
    public void testEquals_true() {
        Edge e1 = new Edge("A", "B", 300);
        Edge e2 = new Edge("A", "B", 300);
        assertTrue(e1.equals(e2));
    }
    
    @Test
    public void testEquals_false() {
        Edge e1 = new Edge("A", "B", 300);
        Edge e2 = new Edge("A", "B", 295);
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testCompareTo() {
        Edge e1 = new Edge("A", "B", 300);
        Edge e2 = new Edge("A", "B", 295);
        assertEquals(1, e1.compareTo(e2));
        assertEquals(-1, e2.compareTo(e1));
        assertEquals(0, e1.compareTo(e1));
    }

    @Test
    public void testToString() {
        Edge e1 = new Edge("A", "B", 300);
        assertEquals("A --- (300.00) --> B", e1.toString());
    }
}