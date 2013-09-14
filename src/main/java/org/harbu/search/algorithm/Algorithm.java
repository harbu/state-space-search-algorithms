package org.harbu.search.algorithm;

import org.harbu.search.problem.Goal;
import org.harbu.search.problem.Heuristic;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.LinkedList;

/**
 * The superclass for all state-space search algorithms.
 *
 * @author Eric Andrews
 * @param <T> the state space type
 */
public abstract class Algorithm<T extends State<T>> {

    protected final T start;
    protected final Goal<T> goal;
    protected final Heuristic<T> heuristic;
    protected final SearchStats stats;

    public Algorithm(Problem<T> problem) {
        this.start = problem.getStartState();
        this.goal = problem.getGoal();
        this.heuristic = problem.getHeuristic();
        this.stats = new SearchStats(getClass().getName(), problem.getClass().getName());
    }
    
    public Result<T> run() {
        Result<T> result = solve();
        result.setStats(stats);
        return result;
    }

    protected abstract Result<T> solve();
}
