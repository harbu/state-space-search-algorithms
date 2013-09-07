/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar.algorithm;

import idastar.problems.Move;
import idastar.problems.Problem;
import java.util.LinkedList;

/**
 * Iterative deepening A* search.
 */
public class IDAStar<T extends Problem<T>> extends Algorithm<T> {

    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int FOUND = -1;

    public IDAStar(T start, T goal) {
        super(start, goal);
    }

    @Override
    public boolean solve() {
        int threshold = heuristic.calculate(start);
        while (true) {
            int result = search(start, 0, threshold);

            if (result == FOUND) {
                return true;
            } else if (result == INFINITY) {
                return false;
            } else {
                threshold = result;
            }
        }
    }

    private int search(T node, int costToNode, int threshold) {
        int f = costToNode + heuristic.calculate(node);

        if (f > threshold) {
            return f;
        } else if (node.equals(goal)) {
            pathToGoal = new LinkedList<>();
            pathToGoal.addFirst(node);
            return FOUND;
        }

        int min = INFINITY;
        for (Move<T> move : node.getMoves()) {
            int t = search(move.getNode(), costToNode + move.getCost(), threshold);
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
