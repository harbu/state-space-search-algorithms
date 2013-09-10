package org.harbu.search.problem.slidingpuzzle;

import org.harbu.search.problem.Goal;

/**
 *
 * @author harbu
 */
public class NPuzzleGoal implements Goal<NPuzzleState> {

    private final NPuzzleState goalState;

    public NPuzzleGoal(NPuzzleState goalState) {
        this.goalState = goalState;
    }

    @Override
    public boolean isGoalReached(NPuzzleState currentState) {
        return currentState.equals(goalState);
    }
}
