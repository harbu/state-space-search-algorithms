package idastar.problems;

import idastar.problems.Problem;

public interface Goal<T extends Problem<T>> {

    public boolean isGoalReached(T currentState);
    
    public Heuristic<T> getHeuristic();
}
