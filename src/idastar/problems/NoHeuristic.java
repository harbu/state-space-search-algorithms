package idastar.problems;

import idastar.problems.Heuristic;
import idastar.problems.Problem;

public class NoHeuristic<T extends Problem<T>> implements Heuristic<T> {

    @Override
    public int calculate(T node) {
        return 0;
    }
}
