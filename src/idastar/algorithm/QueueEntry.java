package idastar.algorithm;

import idastar.problems.Problem;

public class QueueEntry<T extends Problem<T>> implements Comparable<QueueEntry<T>> {

    private final T node;
    private final int g;
    private final int h;

    public QueueEntry(T node, int g, int h) {
        this.node = node;
        this.g = g;
        this.h = h;
    }

    public T getNode() {
        return node;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getF() {
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