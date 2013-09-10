package org.harbu.search.problem.constraint.impl;

import org.harbu.search.problem.constraint.ConstraintSatisfactionState;
import org.harbu.search.util.TwoDIndexConverter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author andrews
 */
public class SudokuProblem extends ConstraintSatisfactionState {

    public static final int NUM_OF_ROWS = 9;
    public static final TwoDIndexConverter indexConverter = new TwoDIndexConverter(NUM_OF_ROWS);

    public SudokuProblem(int[][] layout) {
        super(numOfVariables(layout), makeDomain(), new SudokuConstraint());
        doInitialMoves(layout);
    }

    private SudokuProblem(SudokuProblem original) {
        super(original);
    }

    @Override
    public String toString() {
        String str = "";
        for (int row = 0; row < NUM_OF_ROWS; ++row) {
            if (row % 3 == 0) {
                str += "\n";
            }
            for (int col = 0; col < NUM_OF_ROWS; ++col) {
                if (col % 3 == 0) {
                    str += "|";
                } else {
                    str += " ";
                }
                
                Integer value = getVariables()[indexConverter.twoDTo1D(row, col)];
                if (value != null) {
                    str += value;
                } else {
                    str += " ";
                }
            }
            str += "|\n";
        }
        return str;
    }

    @Override
    protected ConstraintSatisfactionState copy() {
        return new SudokuProblem(this);
    }

    private static int numOfVariables(int[][] layout) {
        return layout.length * layout.length;
    }

    private static Set<Integer> makeDomain() {
        Set<Integer> numbers = new HashSet<>(9);
        for (int i = 1; i <= 9; ++i) {
            numbers.add(i);
        }
        return numbers;
    }

    private void doInitialMoves(int[][] layout) {
        for (int row = 0; row < layout.length; ++row) {
            for (int col = 0; col < layout[row].length; ++col) {
                if (layout[row][col] != 0) {
                    int varIndex = indexConverter.twoDTo1D(row, col);
                    int value = layout[row][col];
                    makeMove(varIndex, value);
                }
            }
        }
    }
}
