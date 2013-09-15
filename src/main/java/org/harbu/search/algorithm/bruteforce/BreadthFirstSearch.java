package org.harbu.search.algorithm.bruteforce;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.harbu.search.algorithm.Result;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;

/**
 *
 * @author Eric Andrews
 */
public class BreadthFirstSearch<T extends State<T>> extends BruteForceSearch<T> {

    private Deque<BruteForceNode<T>> queue;
    private Set<T> closedSet;

    public BreadthFirstSearch(Problem<T> problem) {
        super(problem);
    }

    @Override
    protected Result<T> solve() {
        queue = new LinkedList<>();
        closedSet = new HashSet<>();
        return super.solve();
    }

    @Override
    protected boolean hasOpenListMoreElements() {
        return !queue.isEmpty();
    }

    @Override
    protected void putToOpenList(BruteForceNode<T> node) {
        queue.addLast(node);
        closedSet.add(node.getState());
    }

    @Override
    protected BruteForceNode<T> popFromOpenList() {
        return queue.removeFirst();
    }

    @Override
    protected void handleOperations(BruteForceNode<T> node) {
        for (Operation<T> operation : node.getState().getOperations()) {
            T neighbour = operation.getNode();
            stats.nodeGenerated();

            if (!closedSet.contains(neighbour)) {
                putToOpenList(new BruteForceNode<>(neighbour, node));
            }
        }
    }
}
