package harbu.search.problem.constraint;

import harbu.search.problem.Goal;
import harbu.search.problem.Heuristic;
import harbu.search.problem.NoHeuristic;

public class ConstraintSatisfactionGoal implements Goal<ConstraintSatisfactionState> {

    @Override
    public boolean isGoalReached(ConstraintSatisfactionState currentState) {
        for (Integer variable : currentState.getVariables()) {
            if (variable == null) {
                return false;
            }
        }
        return true;
    }
}
