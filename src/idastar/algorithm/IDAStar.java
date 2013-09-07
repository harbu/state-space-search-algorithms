/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar.algorithm;

import idastar.problems.Heuristic;
import idastar.problems.Move;
import idastar.problems.Problem;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author harbu
 */
public class IDAStar<T extends Problem<T>> {
    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int FOUND = -1;
    
    private final T start;
    private final T goal;
    private LinkedList<T> pathToGoal;
    private final Heuristic<T> heuristic;
    
    public IDAStar(T start, T goal) {
        this.start = start;
        this.goal = goal;
        this.heuristic = goal.createHeuristicToThisNode();
    }
    
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
    
    public List<T> getPathToGoal() {
        return pathToGoal;
    }
}
