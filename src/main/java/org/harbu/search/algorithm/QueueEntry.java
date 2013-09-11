package org.harbu.search.algorithm;

import org.harbu.search.problem.State;

/**
 * A helper data structure for <code>AStar</code>.
 *
 * @author Eric Andrews
 * @param <T> the search state type
 */
public class QueueEntry<T extends State<T>> implements Comparable<QueueEntry<T>> {

    private final T node;
    private final double g;
    private final double h;

    public QueueEntry(T node, double g, double h) {
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
    public int compareTo(QueueEntry<T> o) {
        if (this.getF() < o.getF()) {
            return -1;
        } else if (this.getF() > o.getF()) {
            return 1;
        } else {
            return 0;
        }
    }
}
