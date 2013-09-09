package idastar.problems.constraint;

import idastar.problems.Goal;
import idastar.problems.Heuristic;
import idastar.problems.NoHeuristic;

public class ConstraintSatisfactionGoal implements Goal<ConstraintSatisfactionProblem> {

    @Override
    public boolean isGoalReached(ConstraintSatisfactionProblem currentState) {
        for (Integer variable : currentState.getVariables()) {
            if (variable == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Heuristic<ConstraintSatisfactionProblem> getHeuristic() {
        return new NoHeuristic<>();
    }
}
