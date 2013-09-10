package org.harbu.search.problem.tsp;

import org.harbu.search.problem.Goal;

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