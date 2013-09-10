
package idastar.problems.constraint;

import idastar.problems.NoHeuristic;
import idastar.problems.Problem;

/**
 *
 * @author harbu
 */
public class ConstraintSatisfaction extends Problem<ConstraintSatisfactionState> {
    public ConstraintSatisfaction(ConstraintSatisfactionState startState) {
        setStartState(startState);
        setGoal(new ConstraintSatisfactionGoal());
        setHeuristic(new NoHeuristic<ConstraintSatisfactionState>());
    }
}
