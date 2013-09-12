package org.harbu.search.problem.slidingpuzzle;

import org.harbu.search.problem.Problem;

/**
 * An instance of the sliding tile puzzle.
 *
 * @author Eric Andrews
 */
public class NPuzzle extends Problem<NPuzzleState> {

    public NPuzzle(NPuzzleState startState, NPuzzleState goalState) {
        setStartState(startState);
        setGoal(new NPuzzleGoal(goalState));
        setHeuristic(new NPuzzleHeuristic(goalState));
    }
}
