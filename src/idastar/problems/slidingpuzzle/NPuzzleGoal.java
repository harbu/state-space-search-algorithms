package idastar.problems.slidingpuzzle;

import idastar.problems.Goal;
import idastar.problems.Heuristic;
import idastar.problems.Problem;

/**
 *
 * @author harbu
 */
public class NPuzzleGoal implements Goal {

    private final NPuzzle goalState;
    private final NPuzzleHeuristic heuristic;

    public NPuzzleGoal(NPuzzle goalState) {
        this.goalState = goalState;
        this.heuristic = new NPuzzleHeuristic(goalState);
    }

    @Override
    public boolean isGoalReached(Problem currentState) {
        return currentState.equals(goalState);
    }

    @Override
    public Heuristic getHeuristic() {
        return heuristic;
    }
}
