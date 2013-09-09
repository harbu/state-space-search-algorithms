package idastar.algorithm;

import idastar.problems.Goal;
import idastar.problems.Problem;

public class BidirectionalSearch<T extends Problem<T>> extends Algorithm<T> {

    public BidirectionalSearch(T start, Goal<T> goal) {
        super(start, goal);
    }

    @Override
    public boolean solve() {
        // TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
