package org.harbu.search.problem.constraint.impl;

import org.harbu.search.problem.constraint.ConstraintSatisfactionState;
import java.util.HashSet;
import java.util.Set;

public class NQueensState extends ConstraintSatisfactionState {
    
    private int n;

    public NQueensState(int n) {
        super(n, integersFromZeroTo(n), new NQueensConstraint(n));
        this.n = n;
    }

    private NQueensState(NQueensState original) {
        super(original);
        this.n = original.n;
    }

    @Override
    public String toString() {
        String str = "";
        
        for (int row=0; row < n; ++row) {
            Integer queenCol = getVariables()[row];
            for (int col=0; col < n; ++col) {
                if (queenCol != null && queenCol.equals(col)) {
                    str += "Q";
                } else {
                    str += "X";
                }
            }
            str += "\n";
        }
        
        return str;
    }

    @Override
    protected ConstraintSatisfactionState copy() {
        return new NQueensState(this);
    }

    private static Set<Integer> integersFromZeroTo(int n) {
        Set<Integer> numbers = new HashSet<>(n);
        for (int i = 0; i < n; ++i) {
            numbers.add(i);
        }
        return numbers;
    }
}
