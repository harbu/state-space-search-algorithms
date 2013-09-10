package harbu.search.problem.constraint.impl;

/**
 *
 * @author andrews
 */
public class SudokuException extends RuntimeException {
    private static final String NUM_OF_SUBGRIDS_ERROR =
            "Number of subgrids should be 9, but was given %d";
    
    public SudokuException(String message) {
        super(message);
    }
    
    public static SudokuException makeNumOfSubgridsError(int givenNum) {
        return new SudokuException(String.format(NUM_OF_SUBGRIDS_ERROR, givenNum));
    }
    
}
