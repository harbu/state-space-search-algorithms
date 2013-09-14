package org.harbu.search.algorithm;

import java.util.Deque;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A*-star (best-first search) algorithm implementation.
 * 
 * @author Eric Andrews
 * @param  <T> the state space type
 * 
 * When heuristic always returns zero, this algorithm is essentially becomes
 * uniform-cost search.
 *
 * Good:
 * + Uses heuristic to guide search.
 * + Guaranteed to find best path when using an admissible heuristic.
 * 
 * Bad:
 * - Extremely high memory.
 *
 */
public class AStar<T extends State<T>> extends Algorithm<T> {

    public AStar(Problem<T> problem) {
        super(problem);
    }

    @Override
    protected Result<T> solve() {
        Set<T> closedSet = new HashSet<>();
        AStarOpenList<T> queue = new AStarOpenList<>();
        queue.add(start, null, 0, heuristic.calculate(start));

        while (queue.hasElements()) {
            AStarNode<T> current = queue.removeSmallest();
            closedSet.add(current.getNode());
            stats.nodeExpanded();

            if (goal.isGoalReached(current.getNode())) {
                double totalCost = current.getG();
                Deque<T> path = reconstructPath(current);
                return Result.makeSolution(path, totalCost);
            }

            for (Operation<T> operation : current.getNode().getOperations()) {
                T neighbour = operation.getNode();
                stats.nodeGenerated();
                
                if (!closedSet.contains(neighbour)) {
                    double tentativeG = current.getG() + operation.getCost();
                    if (!queue.containsState(neighbour)) {
                        queue.add(neighbour, current, tentativeG, heuristic.calculate(neighbour));
                    } else if (tentativeG < queue.getNodeFor(neighbour).getG()) {
                        queue.update(neighbour, current, tentativeG);
                    }
                }
            }
        }

        return Result.makeNoSolution();
    }

    private Deque<T> reconstructPath(AStarNode<T> node) {
        Deque<T> pathToGoal = new LinkedList<>();
        while (node != null) {
            pathToGoal.addFirst(node.getNode());
            node = node.getParent();
        }
        pathToGoal.addFirst(start);
        return pathToGoal;
    }
}
