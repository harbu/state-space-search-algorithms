package org.harbu.search.problem;

import java.util.List;

/**
 * Represents a single node or configuration in a search state-space. Most logic
 * pertaining to generating neighbor nodes, checking the equality of two states,
 * and building a string representations, will be implemented in this class.
 *
 * @author Eric Andrews
 * @param <T> the state space type
 */
public interface State<T extends State<T>> {

    /**
     * Get all valid operations from this node to its neighbors.
     *
     * @return a set of valid operations, order is not guaranteed.
     */
    public List<Operation<T>> getOperations();
}
