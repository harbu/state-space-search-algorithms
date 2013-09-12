package org.harbu.search.test;

import org.harbu.search.problem.State;

/**
 *
 * @author harbu
 */
public class TestHelper {

    public static <T extends State<T>> T doSteps(T state, int n) {
        for (int i = 0; i < n; ++i) {
            state = state.getOperations().get(0).getNode();
        }
        return state;
    }
}
