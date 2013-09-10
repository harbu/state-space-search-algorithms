
package harbu.search.problem.constraint;

import harbu.search.problem.NoHeuristic;
import harbu.search.problem.Problem;

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
