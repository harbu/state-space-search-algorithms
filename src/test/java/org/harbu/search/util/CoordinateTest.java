package org.harbu.search.util;

import org.junit.*;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testEquals_notEqual() {
        Coordinate c1 = new Coordinate(3, -2);
        Coordinate c2 = new Coordinate(-2, 3);
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEquals_equal() {
        Coordinate c1 = new Coordinate(6, -2);
        Coordinate c2 = new Coordinate(6, -2);
        assertTrue(c1.equals(c2));
    }

    @Test
    public void testManhattanDistance() {
        Coordinate c1 = new Coordinate(3, 5);
        Coordinate c2 = new Coordinate(-3, 2);
        assertEquals(6 + 3, (int) Coordinate.manhattanDistance(c1, c2));
    }

    @Test
    public void testUp() {
        assertEquals(new Coordinate(0, -1), Coordinate.ORIGIN.up());
    }

    @Test
    public void testDown() {
        assertEquals(new Coordinate(0, 1), Coordinate.ORIGIN.down());
    }

    @Test
    public void testLeft() {
        assertEquals(new Coordinate(-1, 0), Coordinate.ORIGIN.left());
    }

    @Test
    public void testRight() {
        assertEquals(new Coordinate(1, 0), Coordinate.ORIGIN.right());
    }

    @Test
    public void testToString() {
        assertEquals("(3.16, 0.00)", new Coordinate(3.15624, 0).toString());
    }
}