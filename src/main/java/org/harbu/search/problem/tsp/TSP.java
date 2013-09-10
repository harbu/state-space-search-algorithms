package org.harbu.search.problem.tsp;

import org.harbu.search.problem.Problem;

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
