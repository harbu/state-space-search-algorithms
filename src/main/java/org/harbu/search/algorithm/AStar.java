package org.harbu.search.algorithm;

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
 * A*-star best-first search algorithm implementation.
 * 
 * @author Eric Andrews
 * @param  <T> the state space type
 * 
 * When heuristic returns zero, this algorithm is essentially becomes
 * uniform-cost search.
 *
 * Good:
 * + Uses heuristic to guide search.
 * + Guaranteed to find best path.
 * 
 * Bad:
 * - High memory use.
 *
 */
public class AStar<T extends State<T>> extends Algorithm<T> {

    public AStar(Problem<T> problem) {
        super(problem);
    }

    @Override
    public boolean solve() {
        Map<T, T> shortestPathToNodes = new HashMap<>();
        Map<T, Integer> openSet = new HashMap<>();
        Set<T> closedSet = new HashSet<>();
        PriorityQueue<QueueEntry<T>> queue = new PriorityQueue<>();

        queue.add(new QueueEntry<>(start, 0, heuristic.calculate(start)));

        while (!queue.isEmpty()) {
            QueueEntry<T> current = queue.poll();
            openSet.remove(current.getNode());
            closedSet.add(current.getNode());

            if (goal.isGoalReached(current.getNode())) {
                reconstructPath(current.getNode(), shortestPathToNodes);
                return true;
            }

            for (Operation<T> move : current.getNode().getOperations()) {
                T neighbour = move.getNode();
                if (!closedSet.contains(neighbour)) {
                    int tentativeG = current.getG() + move.getCost();
                    if (!openSet.containsKey(neighbour)) {
                        queue.add(new QueueEntry<>(neighbour, tentativeG, heuristic.calculate(neighbour)));
                        shortestPathToNodes.put(neighbour, current.getNode());
                        openSet.put(neighbour, tentativeG);
                    } else if (tentativeG < openSet.get(neighbour)) {
                        queue.add(new QueueEntry<>(neighbour, tentativeG, heuristic.calculate(neighbour)));
                        shortestPathToNodes.put(neighbour, current.getNode());
                    }
                }
            }
        }

        return false;
    }

    private void reconstructPath(T node, Map<T, T> shortestPathToNodes) {
        pathToGoal = new LinkedList<>();
        while (!node.equals(start)) {
            pathToGoal.addFirst(node);
            node = shortestPathToNodes.get(node);
        }
        pathToGoal.addFirst(start);
    }
}
