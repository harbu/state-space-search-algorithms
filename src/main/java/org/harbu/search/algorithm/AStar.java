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
        Map<T, T> shortestPathToNodes = new HashMap<>();
        Map<T, Double> openSet = new HashMap<>();
        Set<T> closedSet = new HashSet<>();
        PriorityQueue<AStarNode<T>> queue = new PriorityQueue<>();

        queue.add(new AStarNode<>(start, 0, heuristic.calculate(start)));

        while (!queue.isEmpty()) {
            AStarNode<T> current = queue.poll();
            openSet.remove(current.getNode());
            closedSet.add(current.getNode());
            stats.nodeExpanded();

            if (goal.isGoalReached(current.getNode())) {
                double totalCost = current.getG();
                Deque<T> path = reconstructPath(current.getNode(), shortestPathToNodes);
                return Result.makeSolution(path, totalCost);
            }

            for (Operation<T> operation : current.getNode().getOperations()) {
                T neighbour = operation.getNode();
                stats.nodeGenerated();
                
                if (!closedSet.contains(neighbour)) {
                    double tentativeG = current.getG() + operation.getCost();
                    if (!openSet.containsKey(neighbour)) {
                        queue.add(new AStarNode<>(neighbour, tentativeG, heuristic.calculate(neighbour)));
                        shortestPathToNodes.put(neighbour, current.getNode());
                        openSet.put(neighbour, tentativeG);
                    } else if (tentativeG < openSet.get(neighbour)) {
                        queue.add(new AStarNode<>(neighbour, tentativeG, heuristic.calculate(neighbour)));
                        shortestPathToNodes.put(neighbour, current.getNode());
                    }
                }
            }
        }

        return Result.makeNoSolution();
    }

    private Deque<T> reconstructPath(T node, Map<T, T> shortestPathToNodes) {
        Deque<T> pathToGoal = new LinkedList<>();
        while (!node.equals(start)) {
            pathToGoal.addFirst(node);
            node = shortestPathToNodes.get(node);
        }
        pathToGoal.addFirst(start);
        return pathToGoal;
    }
}
