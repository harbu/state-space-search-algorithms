package idastar.problems.slidingpuzzle;

import static idastar.problems.slidingpuzzle.NPuzzleState.EMPTY_SPACE;

public class NPuzzleException extends RuntimeException {

    private static final String SIZE_ERROR = "Given array not of size %d x %d.";
    private static final String NUMBER_NOT_IN_RANGE =
            "Numbers must be between 1 and %d or equal to " + EMPTY_SPACE + ".";
    private static final String NUMBER_USED = "Number %d appears more than once.";

    public NPuzzleException(String errorMessage) {
        super(errorMessage);
    }

    public static NPuzzleException makeSizeError(int dimension) {
        return new NPuzzleException(String.format(SIZE_ERROR, dimension, dimension));
    }

    public static NPuzzleException makeRangeError(int dimension) {
        int upperLimit = dimension * dimension - 1;
        return new NPuzzleException(String.format(NUMBER_NOT_IN_RANGE, upperLimit));
    }

    public static NPuzzleException makeNumberUsedError(int number) {
        return new NPuzzleException(String.format(NUMBER_USED, number));
    }
}
