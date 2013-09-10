package harbu.search.util;

import harbu.search.util.Coordinate;
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
        assertEquals(6 + 3, Coordinate.manhattanDistance(c1, c2));
    }
}