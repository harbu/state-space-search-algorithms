package org.harbu.search.problem.maze;

/**
 *
 * @author harbu
 */
public class MazeException extends RuntimeException {

    private static final String START_OR_GOAL_MISSING = "Start or goal tile missing.";
    private static final String INVALID_CHARACTER = "Character %c at (%d, %d) has no meaning";
    private static final String MULTIPLE_STARTS = "More than one start position defined.";
    private static final String MULTIPLE_GOALS = "More than one goal position defined.";

    private MazeException(String message) {
        super(message);
    }

    public static MazeException makeStartOrGoalMissingError() {
        return new MazeException(START_OR_GOAL_MISSING);
    }

    public static MazeException makeStartOrGoalMissingError(char c, int x, int y) {
        String msg = String.format(INVALID_CHARACTER, c, x, y);
        return new MazeException(msg);
    }

    public static MazeException makeMultipleStartsError() {
        throw new MazeException(MULTIPLE_STARTS);
    }

    public static MazeException makeMultipleGoalsError() {
        return new MazeException(MULTIPLE_GOALS);
    }
}
