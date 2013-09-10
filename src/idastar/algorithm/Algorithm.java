package idastar.algorithm;

import idastar.problems.Goal;
import idastar.problems.Heuristic;
import idastar.problems.Problem;
import idastar.problems.State;
import java.util.LinkedList;
import java.util.List;

public abstract class Algorithm<T extends State<T>> {

    protected final T start;
    protected final Goal<T> goal;
    protected final Heuristic<T> heuristic;
    protected LinkedList<State<T>> pathToGoal;

    public Algorithm(Problem<T> problem) {
        this.start = problem.getStartState();
        this.goal = problem.getGoal();
        this.heuristic = problem.getHeuristic();
    }

    public List<State<T>> getPathToGoal() {
        return pathToGoal;
    }

    public abstract boolean solve();
}
