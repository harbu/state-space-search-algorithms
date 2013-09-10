package idastar.problems.constraint.impl;

import idastar.problems.constraint.Constraint;
import static idastar.problems.constraint.impl.SudokuProblem.NUM_OF_ROWS;
import static idastar.problems.constraint.impl.SudokuProblem.indexConverter;
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
        Coordinate coordinate = indexConverter.oneDTo2D(varIndex);
        int varRow = coordinate.getY();
        int varCol = coordinate.getX();

        // Handle horizontal and vertical.
        for (int i = 0; i < NUM_OF_ROWS; ++i) {
            domains.get(indexConverter.twoDTo1D(i, varCol)).remove(valueChosen);
            domains.get(indexConverter.twoDTo1D(varRow, i)).remove(valueChosen);
        }
        
        // Handle 'subgrid'
        int firstElemOfSubgridRow = (varRow / 3) * 3;
        int firstElemOfSubgridCol = (varCol / 3) * 3;
        
        for (int i=0; i < (NUM_OF_ROWS / 3); ++i) {
            for (int j=0; j < (NUM_OF_ROWS / 3); ++j) {
                int index = indexConverter.twoDTo1D(
                        firstElemOfSubgridRow + i,
                        firstElemOfSubgridCol + j);
                System.out.println(index);
                domains.get(index).remove(valueChosen);
            }
        }
    }
}