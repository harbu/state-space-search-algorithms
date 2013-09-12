package org.harbu.search.algorithm;

/**
 * A helper data structure for <code>AStar</code>.
 *
 * @author Eric Andrews
 */
public class AStarNode<T> implements Comparable<AStarNode<T>> {

    private final T node;
    private final double g;
    private final double h;

    public AStarNode(T node, double g, double h) {
        this.node = node;
        this.g = g;
        this.h = h;
    }

    public T getNode() {
        return node;
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

    @Override
    public int compareTo(AStarNode<T> o) {
        if (this.getF() < o.getF()) {
            return -1;
        } else if (this.getF() > o.getF()) {
            return 1;
        } else {
            return 0;
        }
    }
}
