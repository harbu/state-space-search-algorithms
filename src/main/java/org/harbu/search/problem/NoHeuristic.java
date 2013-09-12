package org.harbu.search.problem;

/**
 * This class is used in the absence of a sensible heuristic. It always returns
 * zero.
 *
 * @author Eric Andrews
 * @param <T> the state space type
 */
public class NoHeuristic<T extends State<T>> implements Heuristic<T> {

    @Override
    public double calculate(T node) {
        return 0;
    }
}
