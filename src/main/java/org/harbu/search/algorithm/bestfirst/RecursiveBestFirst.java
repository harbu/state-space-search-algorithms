package org.harbu.search.algorithm.bestfirst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.Result;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;

/**
 *
 * @author Eric Andrews
 */
public class RecursiveBestFirst<T extends State<T>> extends Algorithm<T> {

    private static final double FOUND_GOAL = -1;
    private Node goalNode;
    
    public RecursiveBestFirst(Problem<T> problem) {
        super(problem);
    }

    @Override
    protected Result<T> solve() {
        Node startNode = new Node(start, null, 0, heuristic.calculate(start));
        startNode.setBackup(startNode.getF());
        double result = rbfs(startNode, Double.POSITIVE_INFINITY);
        if (result == FOUND_GOAL) {
            return Result.makeSolution(buildPath(goalNode), goalNode.getG());
        } else {
            return Result.makeNoSolution();
        }
    }

    private double rbfs(Node node, double limit) {
        if (node.getF() > limit) {
            return node.getF();
        }

        if (goal.isGoalReached(node.getState())) {
            goalNode = node;
            return FOUND_GOAL;
        }

        List<Operation<T>> operations = node.getState().getOperations();
        List<Node> successors = asOrderedNodes(operations, node);
        stats.nodeExpanded();

        if (successors.isEmpty()) {
            return Double.POSITIVE_INFINITY;
        } else if (successors.size() == 1) {
            return rbfs(successors.get(0), limit);
        } else {
            while (successors.get(0).getBackup() <= limit) {
                Node first = successors.get(0);
                Node second = successors.get(1);
                double newBackup = rbfs(first, Math.min(limit, second.getBackup()));
                
                if (newBackup == FOUND_GOAL) {
                    return FOUND_GOAL;
                } 
                
                first.setBackup(newBackup);
                Collections.sort(successors);
            }
            return successors.get(0).getBackup();
        }
    }

    private List<Node> asOrderedNodes(List<Operation<T>> operations, Node parent) {
        List<Node> nodes = new ArrayList<>(operations.size());
        for (Operation<T> operation : operations) {
            T neighbor = operation.getNode();
            double g = parent.getG() + operation.getCost();
            double h = heuristic.calculate(neighbor);
            Node node = new Node(neighbor, parent, g, h);
            node.setBackup(Math.max(g + h, parent.getBackup()));
            nodes.add(node);
            stats.nodeGenerated();
        }

        Collections.sort(nodes);
        return nodes;
    }
    
    private Deque<T> buildPath(Node node) {
        Deque<T> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.getState());
            node = node.getParent();
        }
        return path;
    }

    private class Node implements Comparable<Node> {

        private final T state;
        private final Node parent;
        private final double g;
        private final double h;
        private double backup;

        public Node(T state, Node parent, double g, double h) {
            this.state = state;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        public T getState() {
            return state;
        }

        public Node getParent() {
            return parent;
        }

        public double getG() {
            return g;
        }

        public double getH() {
            return h;
        }

        public double getF() {
            return g + h;
        }

        public double getBackup() {
            return backup;
        }

        public void setBackup(double backup) {
            this.backup = backup;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.getBackup(), o.getBackup());
        }     
    }
}
