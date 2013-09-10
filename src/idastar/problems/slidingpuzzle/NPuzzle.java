package idastar.problems.slidingpuzzle;

import idastar.problems.Problem;

public class NPuzzle extends Problem<NPuzzleState> {

    public NPuzzle(NPuzzleState startState, NPuzzleState goalState) {
        setStartState(startState);
        setGoal(new NPuzzleGoal(goalState));
        setHeuristic(new NPuzzleHeuristic(goalState));
    }
}
