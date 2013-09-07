package idastar.util;

import java.util.Arrays;

/**
 * Contains utility functions for handling 2-dimensional arrays.
 *
 * @author Eric Andrews
 */
public class TwoDArrayUtils {

    /**
     * Find first occurrence of value from a 2-dimensional array.
     *
     * @param array 2-d array to search from
     * @param value value to find
     * @return coordinate if found, null otherwise.
     */
    public static Coordinate findValue(int[][] array, int value) {
        for (int y = 0; y < array.length; ++y) {
            for (int x = 0; x < array[y].length; ++x) {
                if (array[y][x] == value) {
                    return new Coordinate(x, y);
                }
            }
        }
        return null;
    }

    /**
     * Make a copy of a 2-dimensional array.
     */
    public static int[][] makeCopy(int[][] array) {
        int[][] copy = new int[array.length][];

        for (int i = 0; i < array.length; ++i) {
            copy[i] = Arrays.copyOf(array[i], array[i].length);
        }

        return copy;
    }

    /**
     * Checks whether given array is of square shape.
     */
    public static boolean isSquare(int[][] array) {
        int height = array.length;
        for (int[] row : array) {
            if (row.length != height) {
                return false;
            }
        }
        return true;
    }
}
