package harbu.search.algorithm;

import harbu.search.problem.Operation;
import harbu.search.problem.Problem;
import harbu.search.problem.State;
import java.util.LinkedList;

/**
 * Iterative deepening A* search.
 */
public class IDAStar<T extends State<T>> extends Algorithm<T> {

    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int FOUND = -1;

    public IDAStar(Problem<T> problem) {
        super(problem);
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
        } else if (goal.isGoalReached(node)) {
            pathToGoal = new LinkedList<>();
            pathToGoal.addFirst(node);
            return FOUND;
        }

        int min = INFINITY;
        for (Operation<T> move : node.getOperations()) {
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
