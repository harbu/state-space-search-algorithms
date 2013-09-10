package org.harbu.search.algorithm;

import org.harbu.search.problem.Goal;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;

/**
 * Depth-first Branch-and-Bound algorithm implementation
 *
 * @author harbu
 */
public class DFBnB<T extends State<T>> extends Algorithm<T> {

    public DFBnB(Problem<T> problem) {
        super(problem);
    }

    @Override
    public boolean solve() {
        // TODO: implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
