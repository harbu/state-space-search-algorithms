package org.harbu.search.algorithm.bestfirst;

import java.util.Deque;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.LinkedList;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.Result;

/**
 * Iterative deepening A* search.
 * 
 * Good:
 *  + Uses heuristic to guide search.
 *  + Guaranteed to find best path.
 *  + Combines best parts of DFS and BFS to achieved optimal asymptotic time- and
 *    space-complexities.
 * Bad:
 *  - Nodes at depths below solution depth expanded several times. Effects become
 *    worse as branching factor gets smaller.
 * 
 * @author Eric Andrews
 * @param  <T> the state space type
 * 
 */
public class IDAStar<T extends State<T>> extends Algorithm<T> {

    private static final double INFINITY = Double.POSITIVE_INFINITY;
    private static final double FOUND = -1;
    
    private Deque<T> pathToGoal;
    private double totalCost;

    public IDAStar(Problem<T> problem) {
        super(problem);
    }

    @Override
    protected Result<T> solve() {
        double threshold = heuristic.calculate(start);
        while (true) {
            double result = search(start, 0, threshold);

            if (result == FOUND) {
                return Result.makeSolution(pathToGoal, totalCost);
            } else if (result == INFINITY) {
                return Result.makeNoSolution();
            } else {
                threshold = result;
            }
        }
    }

    private double search(T node, double costToNode, double threshold) {
        double f = costToNode + heuristic.calculate(node);
        stats.nodeExpanded();

        if (f > threshold) {
            return f;
        } else if (goal.isGoalReached(node)) {
            pathToGoal = new LinkedList<>();
            pathToGoal.addFirst(node);
            totalCost = costToNode;
            return FOUND;
        }

        double min = INFINITY;
        for (Operation<T> operation : node.getOperations()) {
            stats.nodeGenerated();
            double t = search(operation.getNode(), costToNode + operation.getCost(), threshold);
            
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
