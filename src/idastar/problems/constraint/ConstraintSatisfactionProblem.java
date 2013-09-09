package idastar.problems.constraint;

import idastar.problems.Move;
import idastar.problems.Problem;
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
public class ConstraintSatisfactionProblem implements Problem<ConstraintSatisfactionProblem> {

    private static final int NO_MORE_MOVES_INDEX = -1;
    private int varIndex;
    private Constraint constraint;
    private Integer[] variables;
    private List<Set<Integer>> domains;

    public ConstraintSatisfactionProblem(int numOfVariables, Set<Integer> startDomain, Constraint constraint) {
        this.varIndex = 0;
        this.constraint = constraint;
        this.variables = new Integer[numOfVariables];
        this.domains = new ArrayList<>(numOfVariables);

        for (int i = 0; i < numOfVariables; ++i) {
            domains.add(new HashSet<>(startDomain));
        }
    }

    @Override
    public List<Move<ConstraintSatisfactionProblem>> getMoves() {
        List<Move<ConstraintSatisfactionProblem>> moves = new ArrayList<>();
        if (varIndex != NO_MORE_MOVES_INDEX) {
            for (Integer value : domains.get(varIndex)) {
                ConstraintSatisfactionProblem childState = makeMove(value);
                moves.add(new Move<>(childState, 1));
            }
        }

        return moves;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ConstraintSatisfactionProblem)) {
            return false;
        } else {
            ConstraintSatisfactionProblem o = (ConstraintSatisfactionProblem) obj;
            return this.varIndex == o.varIndex
                    && this.constraint == o.constraint
                    && Arrays.equals(this.variables, o.variables);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.varIndex;
        hash = 29 * hash + Objects.hashCode(this.constraint);
        hash = 29 * hash + Arrays.deepHashCode(this.variables);
        return hash;
    }

    public Integer[] getVariables() {
        return variables;
    }
    
    protected ConstraintSatisfactionProblem makeMove(Integer value) {
        return new ConstraintSatisfactionProblem(this, value);
    }

    protected ConstraintSatisfactionProblem(ConstraintSatisfactionProblem previous, Integer valueChosen) {
        this.constraint = previous.constraint;
        this.variables = Arrays.copyOf(previous.variables, previous.variables.length);
        this.variables[previous.varIndex] = valueChosen;
        this.domains = deepCopyDomains(previous.domains);
        constraint.apply(previous.varIndex, valueChosen, this.domains);
        this.varIndex = chooseNextVarIndex();

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
