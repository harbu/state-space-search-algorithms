package org.harbu.search.problem.tsp;

import org.harbu.search.problem.Problem;

/**
 * An instance of the Traveling salesman problem in which we are looking for the
 * shortest route that starts from a vertex X, goes through all other vertices
 * exactly one, and then returns back to X.
 *
 * @author Eric Andrews
 */
public class TSP extends Problem<TSPState> {

    public TSP(TSPState startState) {
        setStartState(startState);
        setGoal(new TSPGoal());
        setHeuristic(new TSPHeuristic());
    }
}
