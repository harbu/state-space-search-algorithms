package org.harbu.search.problem.constraint;

import org.harbu.search.problem.Goal;

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
