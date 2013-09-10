package idastar.problems.tsp;

import idastar.problems.Goal;

/**
 *
 * @author harbu
 */
public class TSPGoal implements Goal<TSPState> {

    @Override
    public boolean isGoalReached(TSPState currentState) {
        return currentState.isReturnedHome();
    }
}