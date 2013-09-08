package idastar.algorithm;

import idastar.problems.Goal;
import idastar.problems.Move;
import idastar.problems.Problem;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A*-star best-first search algorithm implementation.
 * 
 * When heuristic returns zero, this algorithm is essentially becomes
 * uniform-cost search.
 * 
 */
public class AStar<T extends Problem<T>> extends Algorithm<T> {

    public AStar(T start, Goal<T> goal) {
        super(start, goal);
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

            for (Move<T> move : current.getNode().getMoves()) {
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
