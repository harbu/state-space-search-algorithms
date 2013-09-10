package harbu.search.algorithm;

import harbu.search.problem.Goal;
import harbu.search.problem.Heuristic;
import harbu.search.problem.Problem;
import harbu.search.problem.State;
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
