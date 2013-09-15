package org.harbu.search.algorithm.bruteforce;

import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.Deque;
import java.util.LinkedList;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.Result;

/**
 * Implementations of basic breadth-first and depth-first search algorithms.
 *
 * @author Eric Andrews
 * @param <T> the state space type
 *
 * Good: + Both are conceptually simple. + BFS: Guaranteed to find best path, if
 * unit-cost edges. + DFS: low memory use, asymptotically equal to depth of
 * search.
 *
 * Bad: - Neither use heuristic to guide search. - DFS: can end up in an
 * infinite path when searching graphs. - DFS: not guaranteed find shortest
 * path. - BFS: high memory use.
 *
 */
public abstract  class BruteForceSearch<T extends State<T>> extends Algorithm<T> {

    public BruteForceSearch(Problem<T> problem) {
        super(problem);
    }

    @Override
    protected Result<T> solve() {
        putToOpenList(new BruteForceNode<>(start, null));

        while (hasOpenListMoreElements()) {
            BruteForceNode<T> node = popFromOpenList();
            stats.nodeExpanded();

            if (goal.isGoalReached(node.getState())) {
                return Result.makeSolution(rebuildPath(node), 0);
            }
            handleOperations(node);
        }

        return Result.makeNoSolution();
    }
    
    protected abstract boolean hasOpenListMoreElements();

    protected abstract void putToOpenList(BruteForceNode<T> node);
    
    protected abstract  BruteForceNode<T> popFromOpenList();

    protected abstract void handleOperations(BruteForceNode<T> node);
    
    private Deque<T> rebuildPath(BruteForceNode<T> node) {
        LinkedList<T> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.getState());
            node = node.getParent();
        }
        return path;
    }

}
