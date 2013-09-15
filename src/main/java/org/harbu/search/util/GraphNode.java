package org.harbu.search.util;

import com.google.common.collect.ImmutableMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Eric Andrews
 */
public class GraphNode {

    private final String identifier;
    private final Map<GraphNode, Double> children;

    public GraphNode(String identifier) {
        this.identifier = identifier;
        this.children = new LinkedHashMap<>();
    }

    public String getIdentifier() {
        return identifier;
    }
    
    public void addChildren(GraphNode ... children) {
        for (GraphNode child : children) {
            addChild(child, 1);
        }
    }

    public void addChild(GraphNode child, double cost) {
        children.put(child, cost);
    }

    public Map<GraphNode, Double> getChildren() {
        return ImmutableMap.copyOf(children);
    }
}
