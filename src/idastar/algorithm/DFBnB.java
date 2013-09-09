package idastar.algorithm;

import idastar.problems.Goal;
import idastar.problems.Problem;

/**
 * Depth-first Branch-and-Bound algorithm implementation
 *
 * @author harbu
 */
public class DFBnB<T extends Problem<T>> extends Algorithm<T> {

    public DFBnB(T start, Goal<T> goal) {
        super(start, goal);
    }

    @Override
    public boolean solve() {
        // TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
