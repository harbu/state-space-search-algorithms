package idastar.problems.constraint;

import idastar.problems.Goal;
import idastar.problems.Heuristic;
import idastar.problems.NoHeuristic;

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
