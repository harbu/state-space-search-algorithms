package idastar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An undirected graph where each edge is connected to each other. 
 * @author harbu
 */
public class CompleteGraph {
    private final List<String> labels;
    private final int[][] edgeCosts;

    public CompleteGraph(List<String> labels, int[][] upperHalfCostMatrix) {
        this.labels = labels;
        this.edgeCosts = new int[labels.size()][labels.size()];
        readInLowerHalfMatrix(upperHalfCostMatrix);
    }
    
    private CompleteGraph(CompleteGraph superGraph, List<String> labelsToRetain) {
        this.labels = labelsToRetain;
        this.edgeCosts = new int[this.labels.size()][this.labels.size()];
        
        for (int i=0; i < labels.size(); ++i) {
            String from = labels.get(i);
            for (int j=0; j < labels.size(); ++j) {
                String to = labels.get(j);
                edgeCosts[i][j] = superGraph.cost(from, to);
            }
        }
    }
    
    public int cost(String fromLabel, String toLabel) {
        int from = labels.indexOf(fromLabel);
        int to = labels.indexOf(toLabel);
        return edgeCosts[from][to];
    }
    
    public Set<String> getVertices() {
        return new HashSet<>(labels);
    }
    
    public Map<String, List<Edge>> getEdges() {
        Map<String, List<Edge>> edges = new HashMap<>();
        
        for (int row=0; row < labels.size(); ++row) {
            String from = labels.get(row);
            ArrayList<Edge> edgesForNode = new ArrayList<>();
            
            for (int col=0; col < labels.size(); ++col) {
                if (row != col) {
                    edgesForNode.add(new Edge(from, labels.get(col), edgeCosts[row][col]));
                }
            }
            
            edges.put(from, edgesForNode);
        }
        
        return edges;
    }
    
    public CompleteGraph subGraph(Set<String> labelsToRetain) {
        return new CompleteGraph(this, new ArrayList<>(labelsToRetain));
    }

    private void readInLowerHalfMatrix(int[][] upperHalfCostMatrix) {
        for (int row=labels.size() - 1; row != 0; --row) {
            for (int col=0; col < row; ++col) {
                edgeCosts[row][col] = upperHalfCostMatrix[row][col];
                edgeCosts[col][row] = upperHalfCostMatrix[row][col];
            }
        }
        for (int[] row : edgeCosts) {
            System.out.println(Arrays.toString(row));
        }
    } 
}
