package idastar.algorithm;

import idastar.problems.Heuristic;
import idastar.problems.Problem;
import java.util.LinkedList;
import java.util.List;

public abstract class Algorithm<T extends Problem<T>> {

    protected final T start;
    protected final T goal;
    protected final Heuristic<T> heuristic;
    protected LinkedList<T> pathToGoal;

    public Algorithm(T start, T goal) {
        this.start = start;
        this.goal = goal;
        this.heuristic = goal.createHeuristicToThisNode();
    }

    public List<T> getPathToGoal() {
        return pathToGoal;
    }

    public abstract boolean solve();
}
