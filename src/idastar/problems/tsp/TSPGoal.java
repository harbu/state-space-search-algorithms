package idastar.problems.tsp;

import idastar.problems.Goal;
import idastar.problems.Heuristic;

/**
 *
 * @author harbu
 */
public class TSPGoal implements Goal<TSP> {

    @Override
    public boolean isGoalReached(TSP currentState) {
        return currentState.isReturnedHome();
    }

    @Override
    public Heuristic<TSP> getHeuristic() {
        return new TSPHeuristic();
    }
}