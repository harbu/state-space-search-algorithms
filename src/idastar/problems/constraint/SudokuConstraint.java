package idastar.problems.constraint;

import idastar.util.Coordinate;
import java.util.List;
import java.util.Set;

/**
 *
 * @author andrews
 */
class SudokuConstraint implements Constraint {

    @Override
    public void apply(int varIndex, Integer valueChosen, List<Set<Integer>> domains) {
        Coordinate coordinate = convert1dIndexTo2d(varIndex);
        int varRow = coordinate.getY();
        int varCol = coordinate.getX();

        for (int i = 0; i < SudokuProblem.NUM_OF_ROWS; ++i) {
            domains.get(convert2dIndexTo1d(i, varCol)).remove(valueChosen);
            domains.get(convert2dIndexTo1d(varRow, i)).remove(valueChosen);
        }
    }

    private static int convert2dIndexTo1d(int row, int col) {
        return row * SudokuProblem.NUM_OF_ROWS + col;
    }

    private static Coordinate convert1dIndexTo2d(int index) {
        int row = index / SudokuProblem.NUM_OF_ROWS;
        int col = index % SudokuProblem.NUM_OF_ROWS;
        return new Coordinate(col, row);
    }
}