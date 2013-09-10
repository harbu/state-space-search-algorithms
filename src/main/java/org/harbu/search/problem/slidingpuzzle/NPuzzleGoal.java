package org.harbu.search.problem.slidingpuzzle;

import org.harbu.search.problem.Goal;
import org.harbu.search.problem.State;

/**
 *
 * @author harbu
 */
public class NPuzzleGoal implements Goal<NPuzzleState> {

    private final NPuzzleState goalState;
    private final NPuzzleHeuristic heuristic;

    public NPuzzleGoal(NPuzzleState goalState) {
        this.goalState = goalState;
        this.heuristic = new NPuzzleHeuristic(goalState);
    }

    @Override
    public boolean isGoalReached(NPuzzleState currentState) {
        return currentState.equals(goalState);
    }
}
