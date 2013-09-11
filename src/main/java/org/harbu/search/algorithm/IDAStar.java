package org.harbu.search.algorithm;

import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.LinkedList;

/**
 * Iterative deepening A* search.
 * 
 * @author Eric Andrews
 * @param  <T> the state space type
 * 
 * Good: + Uses heuristic to guide search. + Guaranteed to find best path. +
 * Combines best parts of DFS and BFS to achieved optimal asymptotic time- and
 * space-complexities. Bad: - Some nodes expanded several times, gets worse as
 * branching factor gets smaller.
 */
public class IDAStar<T extends State<T>> extends Algorithm<T> {

    private static final double INFINITY = Double.POSITIVE_INFINITY;
    private static final double FOUND = -1;

    public IDAStar(Problem<T> problem) {
        super(problem);
    }

    @Override
    public boolean solve() {
        double threshold = heuristic.calculate(start);
        while (true) {
            double result = search(start, 0, threshold);

            if (result == FOUND) {
                return true;
            } else if (result == INFINITY) {
                return false;
            } else {
                threshold = result;
            }
        }
    }

    private double search(T node, double costToNode, double threshold) {
        double f = costToNode + heuristic.calculate(node);

        if (f > threshold) {
            return f;
        } else if (goal.isGoalReached(node)) {
            pathToGoal = new LinkedList<>();
            pathToGoal.addFirst(node);
            totalCost = costToNode;
            return FOUND;
        }

        double min = INFINITY;
        for (Operation<T> move : node.getOperations()) {
            double t = search(move.getNode(), costToNode + move.getCost(), threshold);
            if (t == FOUND) {
                pathToGoal.addFirst(node);
                return FOUND;
            } else if (t < min) {
                min = t;
            }
        }

        return min;
    }
}
