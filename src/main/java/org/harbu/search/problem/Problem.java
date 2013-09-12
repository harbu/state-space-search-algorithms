package org.harbu.search.problem;

/**
 * Represents a graph-search or tree-search problem instance. Glues together the
 * necessary objects to solve a search problem, i.e. the start state, the goal
 * and a heuristic function.
 *
 * @author Eric Andrews
 * @param <T> the state space type
 */
public abstract class Problem<T extends State<T>> {

    private T startState;
    private Goal<T> goal;
    private Heuristic<T> heuristic;

    public T getStartState() {
        return startState;
    }

    public Goal<T> getGoal() {
        return goal;
    }

    public Heuristic<T> getHeuristic() {
        return heuristic;
    }

    protected void setStartState(T startState) {
        this.startState = startState;
    }

    protected void setGoal(Goal<T> goal) {
        this.goal = goal;
    }

    protected void setHeuristic(Heuristic<T> heuristic) {
        this.heuristic = heuristic;
    }
}
