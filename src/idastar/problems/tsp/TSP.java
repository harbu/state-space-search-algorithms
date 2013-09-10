package idastar.problems.tsp;

import idastar.problems.Problem;

/**
 *
 * @author harbu
 */
public class TSP extends Problem<TSPState> {

    public TSP(TSPState startState) {
        setStartState(startState);
        setGoal(new TSPGoal());
        setHeuristic(new TSPHeuristic());
    }
}
