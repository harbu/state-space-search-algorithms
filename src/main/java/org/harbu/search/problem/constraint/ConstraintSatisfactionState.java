package org.harbu.search.problem.constraint;

import org.harbu.search.problem.Operation;
import org.harbu.search.problem.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author harbu
 */
public class ConstraintSatisfactionState implements State<ConstraintSatisfactionState> {

    private static final int NO_MORE_MOVES_INDEX = -1;
    private Constraint constraint;
    private Integer[] variables;
    private List<Set<Integer>> domains;

    public ConstraintSatisfactionState(int numOfVariables, Set<Integer> startDomain, Constraint constraint) {
        this.constraint = constraint;
        this.variables = new Integer[numOfVariables];
        this.domains = new ArrayList<>(numOfVariables);

        for (int i = 0; i < numOfVariables; ++i) {
            domains.add(new HashSet<>(startDomain));
        }
    }
    
    protected ConstraintSatisfactionState(ConstraintSatisfactionState original) {
        this.constraint = original.constraint;
        this.variables = Arrays.copyOf(original.variables, original.variables.length);
        this.domains = deepCopyDomains(original.domains);
    }
    
    protected void makeMove(int varIndex, int valueChosen) {
        variables[varIndex] = valueChosen;
        constraint.apply(varIndex, valueChosen, domains);
    }

    @Override
    public List<Operation<ConstraintSatisfactionState>> getOperations() {
        List<Operation<ConstraintSatisfactionState>> moves = new ArrayList<>();
        int varIndex = chooseNextVarIndex();
        
        if (varIndex != NO_MORE_MOVES_INDEX) {
            for (Integer value : domains.get(varIndex)) {
                ConstraintSatisfactionState childState = copy();
                childState.makeMove(varIndex, value);
                moves.add(new Operation<>(childState, 1));
            }
        }

        return moves;
    }
    
    public Integer[] getVariables() {
        return variables;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ConstraintSatisfactionState)) {
            return false;
        } else {
            ConstraintSatisfactionState o = (ConstraintSatisfactionState) obj;
            return this.domains.equals(o.domains)
                    && this.constraint == o.constraint
                    && Arrays.equals(this.variables, o.variables);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.constraint);
        hash = 13 * hash + Arrays.deepHashCode(this.variables);
        hash = 13 * hash + Objects.hashCode(this.domains);
        return hash;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i=0; i < variables.length; ++i) {
            str += "Variable " + i + ": ";
            if (variables[i] == null) {
                str += domains.get(i);
            } else {
                str += variables[i];
            }
            str += "\n";
        }
        return str;
    }
    
    protected ConstraintSatisfactionState copy() {
        return new ConstraintSatisfactionState(this);
    }

    private List<Set<Integer>> deepCopyDomains(List<Set<Integer>> domains) {
        List<Set<Integer>> copyOfDomains = new ArrayList<>(domains.size());
        for (Set<Integer> domain : domains) {
            copyOfDomains.add(new HashSet<>(domain));
        }
        return copyOfDomains;
    }

    private int chooseNextVarIndex() {
        int nextVarIndex = NO_MORE_MOVES_INDEX;
        int smallestDomainSize = Integer.MAX_VALUE;

        for (int i = 0; i < variables.length; ++i) {
            if (variables[i] == null) {
                int domainSize = domains.get(i).size();

                if (domainSize < smallestDomainSize) {
                    smallestDomainSize = domainSize;
                    nextVarIndex = i;
                }
            }
        }

        return nextVarIndex;
    }
}
