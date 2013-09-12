package org.harbu.search.util;

/**
 * Convert between a two-dimensional and one-dimensional array index.
 * 
 * @author Eric Andrews
 */
public class TwoDIndexConverter {

    private final int numOfRows;

    /**
     * Instantiate a converter.
     * @param numOfRows the number of rows in the target 2-d representation of
     *                  an array.
     */
    public TwoDIndexConverter(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int twoDTo1D(int row, int col) {
        return row * numOfRows + col;
    }

    public Coordinate oneDTo2D(int index) {
        int row = index / numOfRows;
        int col = index % numOfRows;
        return new Coordinate(col, row);
    }
}
