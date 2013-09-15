package org.harbu.search.algorithm.bestfirst;

import org.harbu.search.algorithm.bestfirst.AStarNode;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import org.harbu.search.problem.State;

/**
 *
 * @author Eric Andrews
 */
public class AStarOpenList<T extends State<T>> {
    private Map<T, AStarNode<T>> statesToNodes;
    private PriorityQueue<AStarNode<T>> queue;

    public AStarOpenList() {
        statesToNodes = new HashMap<>();
        queue = new PriorityQueue<>();
    }
    
    public boolean hasElements() {
        return !queue.isEmpty();
    }
    
    public void add(T state, AStarNode<T> parentNode, double g, double h) {
        AStarNode<T> node = new AStarNode<>(state, parentNode, g, h);
        queue.add(node);
        statesToNodes.put(state, node);
    }
    
    public AStarNode<T> removeSmallest() {
        AStarNode<T> node = queue.remove();
        statesToNodes.remove(node.getNode());
        return node;
    }
    
    public boolean containsState(T stateToLookFor) {
        return statesToNodes.containsKey(stateToLookFor);
    }
    
    public AStarNode<T> getNodeFor(T state) {
        return statesToNodes.get(state);
    }
    
    public void update(T state, AStarNode<T> parentNode, double g) {
        double h = statesToNodes.get(state).getH();
        queue.remove(makeEmptyNode(state));
        add(state, parentNode, g, h);
    }

    private AStarNode<T> makeEmptyNode(T stateToLookFor) {
        return new AStarNode<>(stateToLookFor, null, 0, 0);
    }
    
    
}
