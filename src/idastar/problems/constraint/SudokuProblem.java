package idastar.problems.constraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author andrews
 */
public class SudokuProblem extends ConstraintSatisfactionProblem {
    
    public SudokuProblem(int[][] layout) {
        super(numOfVariables(layout), makeDomain(), new SudokuConstraint());
    }
    
    private static int numOfVariables(int[][] layout) {
        return layout.length * layout.length;
    }
    
    private static Set<Integer> makeDomain() {
        Set<Integer> numbers = new HashSet<>(9);
        for (int i=1; i <= 9; ++i) {
            numbers.add(i);
        }
        return numbers;
    }
}
