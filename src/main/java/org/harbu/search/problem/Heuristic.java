package org.harbu.search.problem;

/**
 * A heuristic that can be used in best-first searches to approximate the
 * remaining cost from a node to the goal. Should be both admissible, and in
 * some cases, consistent for optimal-cost solutions.
 * @author    Eric Andrews
 * @param <T> the state space type
 */
public interface Heuristic<T extends State<T>> {

    public double calculate(T node);
}
