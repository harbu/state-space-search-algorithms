package idastar.util;

/**
 *
 * @author harbu
 */
public class TwoDIndexConverter {

    private final int numOfRows;

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
