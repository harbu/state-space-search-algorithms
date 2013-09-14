package org.harbu.search.algorithm;

import com.google.common.collect.ImmutableList;
import java.util.Deque;
import java.util.List;
import org.harbu.search.problem.State;

/**
 * The results of a run of an <class>Algorithm</code>.
 * @author Eric Andrews
 */
public class Result<T extends State<T>> {

    private Deque<T> path;
    private T goalState;
    private double totalCost;

    private Result() {
        this.path = null;
        this.goalState = null;
        this.totalCost = Double.POSITIVE_INFINITY;
    }

    private Result(Deque<T> path, double totalCost) {
        this.path = path;
        this.goalState = path.getLast();
        this.totalCost = totalCost;
    }

    public List<T> getPath() {
        return ImmutableList.copyOf(path);
    }

    public T getGoalState() {
        return goalState;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getDepth() {
        if (path == null) {
            return -1;
        } else {
            return path.size();
        }
    }
    
    public boolean foundSolution() {
        return goalState != null;
    }

    public static <T extends State<T>> Result<T> makeSolution(Deque<T> path, double totalCost) {
        return new Result<>(path, totalCost);
    }

    public static <T extends State<T>> Result<T> makeNoSolution() {
        return new Result<>();
    }
}
