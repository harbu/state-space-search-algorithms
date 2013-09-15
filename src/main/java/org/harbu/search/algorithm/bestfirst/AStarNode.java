package org.harbu.search.algorithm.bestfirst;

import java.util.Objects;

/**
 * A helper data structure for <code>AStar</code>.
 *
 * @author Eric Andrews
 */
public class AStarNode<T> implements Comparable<AStarNode<T>> {
    private final T node;
    private final AStarNode<T> parent;
    private final double g;
    private final double h;

    public AStarNode(T node, AStarNode<T> parent, double g, double h) {
        this.node = node;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    public T getNode() {
        return node;
    }

    public AStarNode<T> getParent() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AStarNode)) {
            return false;
        } else {
            return this.node == ((AStarNode) obj).node;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.node);
        return hash;
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
