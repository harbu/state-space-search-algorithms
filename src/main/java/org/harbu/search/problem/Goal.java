package org.harbu.search.problem;

/**
 * Defines the goal of a state-space search problem. The goal can be anything
 * from a single explicit node, to a set of constraints that must be satisfied.
 *
 * @author    Eric Andrews
 * @param <T> the state space type
 */
public interface Goal<T extends State<T>> {

    public boolean isGoalReached(T currentState);
}
