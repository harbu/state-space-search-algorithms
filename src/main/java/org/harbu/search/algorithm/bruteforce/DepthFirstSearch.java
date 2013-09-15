package org.harbu.search.algorithm.bruteforce;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.harbu.search.algorithm.Result;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;

/**
 *
 * @author Eric Andrews
 */
public class DepthFirstSearch<T extends State<T>> extends BruteForceSearch<T> {

    private Stack<BruteForceNode<T>> stack;

    public DepthFirstSearch(Problem<T> problem) {
        super(problem);
    }

    @Override
    protected Result<T> solve() {
        stack = new Stack<>();
        return super.solve();
    }

    @Override
    protected boolean hasOpenListMoreElements() {
        return !stack.isEmpty();
    }

    @Override
    protected void putToOpenList(BruteForceNode<T> node) {
        stack.push(node);
    }

    @Override
    protected BruteForceNode<T> popFromOpenList() {
        return stack.pop();
    }

    @Override
    protected void handleOperations(BruteForceNode<T> node) {
        for (Operation<T> operation : Lists.reverse(node.getState().getOperations())) {
            T neighbour = operation.getNode();
            stats.nodeGenerated();
            putToOpenList(new BruteForceNode<>(neighbour, node));
        }
    }
}
