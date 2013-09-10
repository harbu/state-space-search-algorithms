package org.harbu.search.problem;

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
