package org.harbu.search.problem.constraint.impl;

import org.harbu.search.problem.constraint.Constraint;
import static org.harbu.search.problem.constraint.impl.SudokuState.NUM_OF_ROWS;
import static org.harbu.search.problem.constraint.impl.SudokuState.indexConverter;
import org.harbu.search.util.Coordinate;
import java.util.List;
import java.util.Set;

/**
 *
 * @author andrews
 */
class SudokuConstraint implements Constraint {

    @Override
    public void apply(int varIndex, int valueChosen, List<Set<Integer>> domains) {
        Coordinate coordinate = indexConverter.oneDTo2D(varIndex);
        int varRow = (int) coordinate.getY();
        int varCol = (int) coordinate.getX();

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
                domains.get(index).remove(valueChosen);
            }
        }
    }
}