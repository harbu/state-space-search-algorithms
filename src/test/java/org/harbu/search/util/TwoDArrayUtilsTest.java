package org.harbu.search.util;

import org.junit.*;
import static org.junit.Assert.*;

public class TwoDArrayUtilsTest {

    private int[][] array;

    @Before
    public void setUp() {
        this.array = new int[][]{
            {4, 0, 3, -7, 5},
            {15, 623, -42},
            {-13, 5, 5, 5}
        };
    }

    @Test
    public void testFindValue_notFound() {
        assertNull(TwoDArrayUtils.findValue(array, 666));
    }

    @Test
    public void testFindValue_found() {
        assertEquals(new Coordinate(4, 0), TwoDArrayUtils.findValue(array, 5));
    }

    @Test
    public void testGetValueAt() {
        Coordinate coordinate = new Coordinate(1, 1);
        assertEquals(623, TwoDArrayUtils.getValueAt(coordinate, array));
    }

    @Test
    public void testMakeCopy() {
        int[][] copy = TwoDArrayUtils.makeCopy(array);
        assertNotSame(array, copy);
        assertArrayEquals(array, copy);
    }

    @Test
    public void testIsSquare_false() {
        assertFalse(TwoDArrayUtils.isSquare(array));
    }

    @Test
    public void testIsSquare_true() {
        array = new int[][]{
            {1, 2},
            {3, 4}
        };

        assertTrue(TwoDArrayUtils.isSquare(array));
    }

    @Test
    public void testIsSquare_emptyTrue() {
        assertTrue(TwoDArrayUtils.isSquare(new int[][]{}));
    }
}