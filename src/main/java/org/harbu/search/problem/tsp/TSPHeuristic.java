package org.harbu.search.problem.tsp;

import org.harbu.search.problem.Heuristic;
import org.harbu.search.util.CompleteGraph;
import org.harbu.search.util.Edge;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Uses Primm's algorithm to create the minimum spanning tree for the graph but
 * only sums up the costs of all edges and returns that.
 *
 * @author harbu
 */
public class TSPHeuristic implements Heuristic<TSPState> {

    @Override
    public double calculate(TSPState node) {
        double total = 0;
        List<String> nodesHandled = new ArrayList<>();
        Set<String> nodesLeft = new HashSet<>(node.getCitiesNotYetVisited());
        nodesLeft.add(node.getCurrentCity());
        CompleteGraph subGraph = node.getGraph().subGraph(new ArrayList<>(nodesLeft));
        Map<String, Set<Edge>> edgesByVertex = subGraph.getEdges();

        if (nodesLeft.isEmpty()) {
            return 0;
        }
        
        String nodeLabel = node.getCurrentCity();
        nodesLeft.remove(nodeLabel);
        nodesHandled.add(nodeLabel);

        while (!nodesLeft.isEmpty()) {
            total += findMinimumEdge(nodesHandled, edgesByVertex, nodesLeft);
        }

        return total;
    }

    private double findMinimumEdge(List<String> nodesHandled, Map<String, Set<Edge>> edgesByVertex, Set<String> nodesLeft) {
        double minCost = Double.POSITIVE_INFINITY;
        Edge minEdge = null;
        for (String from : nodesHandled) {
            for (Edge edge : edgesByVertex.get(from)) {
                if (nodesLeft.contains(edge.getTo())) {
                    if (edge.getCost() < minCost) {
                        minCost = edge.getCost();
                        minEdge = edge;
                    }
                }
            }
        }
        nodesLeft.remove(minEdge.getTo());
        nodesHandled.add(minEdge.getTo());
        return minEdge.getCost();
    }
}
