package org.harbu.search.algorithm.bruteforce;

/**
 *
 * @author Eric Andrews
 */
public class BruteForceNode<T> {

    private final T state;
    private final BruteForceNode<T> parent;

    public BruteForceNode(T state, BruteForceNode<T> parent) {
        this.state = state;
        this.parent = parent;
    }

    public T getState() {
        return state;
    }

    public BruteForceNode<T> getParent() {
        return parent;
    }
}
