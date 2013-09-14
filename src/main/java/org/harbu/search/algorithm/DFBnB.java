package org.harbu.search.algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;

/**
 * Depth-first Branch-and-Bound algorithm implementation. Requires the search
 * tree to be finite.
 *
 * @author harbu
 */
public class DFBnB<T extends State<T>> extends Algorithm<T> {

    private final Comparator<Operation<T>> comparator;
    private Deque<T> currentPath;
    private Deque<T> bestPath;
    private double minCostSolution;

    public DFBnB(Problem<T> problem) {
        super(problem);
        comparator = new AscendingOperatorComparator();
    }

    @Override
    public Result<T> solve() {
        currentPath = new LinkedList<>();
        minCostSolution = Double.POSITIVE_INFINITY;

        solveBranch(start, 0);

        if (bestPath == null) {
            return Result.makeNoSolution();
        } else {
            return Result.makeSolution(bestPath, minCostSolution);
        }
    }

    private void solveBranch(T node, double costToNode) {

        currentPath.addLast(node);

        double f = costToNode + heuristic.calculate(node);
        if (f < minCostSolution) {
            if (goal.isGoalReached(node)) {
                bestPath = new LinkedList<>(currentPath);
                minCostSolution = f;
            } else {
                for (Operation<T> operation : operationsInAscendingOrder(node)) {
                    solveBranch(operation.getNode(), costToNode + operation.getCost());
                }
            }
        }

        currentPath.removeLast();
    }

    private List<Operation<T>> operationsInAscendingOrder(T node) {
        List<Operation<T>> operations = node.getOperations();
        Collections.sort(operations, comparator);
        return operations;
    }

    private class AscendingOperatorComparator implements Comparator<Operation<T>> {

        @Override
        public int compare(Operation<T> o1, Operation<T> o2) {
            double f1 = o1.getCost() + heuristic.calculate(o1.getNode());
            double f2 = o2.getCost() + heuristic.calculate(o2.getNode());
            return Double.compare(f1, f2);
        }
    }
}
