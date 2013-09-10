package org.harbu.search.algorithm;

import org.harbu.search.problem.Goal;
import org.harbu.search.problem.Heuristic;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.LinkedList;
import java.util.List;

/**
 * The superclass for all search algorithms.
 *
 * @author Eric Andrews
 * @param <T> the state space type
 */
public abstract class Algorithm<T extends State<T>> {

    protected final T start;
    protected final Goal<T> goal;
    protected final Heuristic<T> heuristic;
    protected LinkedList<T> pathToGoal;

    public Algorithm(Problem<T> problem) {
        this.start = problem.getStartState();
        this.goal = problem.getGoal();
        this.heuristic = problem.getHeuristic();
    }

    public List<T> getPathToGoal() {
        return pathToGoal;
    }

    public abstract boolean solve();
}
