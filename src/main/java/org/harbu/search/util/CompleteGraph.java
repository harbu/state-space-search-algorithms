package org.harbu.search.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An undirected graph where all vertices are connected by an edge. Internally
 * stored as an adjacency matrix.
 * @author harbu
 */
public class CompleteGraph {
    private final int totalVertices;
    private final List<String> labels;
    private final double[][] edgeCosts;

    public CompleteGraph(List<String> labels, double[][] lowerHalfCostMatrix) {
        this.totalVertices = labels.size();
        this.labels = labels;
        this.edgeCosts = new double[totalVertices][totalVertices];
        readInLowerHalfMatrix(lowerHalfCostMatrix);
    }
    
    private CompleteGraph(CompleteGraph superGraph, List<String> labelsToKeep) {
        this.totalVertices = labelsToKeep.size();
        this.labels = labelsToKeep;
        this.edgeCosts = new double[this.totalVertices][this.totalVertices];
        
        for (int i=0; i < totalVertices; ++i) {
            String from = labels.get(i);
            for (int j=0; j < totalVertices; ++j) {
                String to = labels.get(j);
                edgeCosts[i][j] = superGraph.cost(from, to);
            }
        }
    }
    
    public double cost(String fromLabel, String toLabel) {
        int from = labels.indexOf(fromLabel);
        int to = labels.indexOf(toLabel);
        return edgeCosts[from][to];
    }
    
    public Set<String> getVertices() {
        return new HashSet<>(labels);
    }
    
    public Map<String, Set<Edge>> getEdges() {
        Map<String, Set<Edge>> edges = new HashMap<>();
        
        for (int row=0; row < totalVertices; ++row) {
            String from = labels.get(row);
            Set<Edge> edgesForNode = new HashSet<>();
            
            for (int col=0; col < totalVertices; ++col) {
                if (row != col) {
                    edgesForNode.add(new Edge(from, labels.get(col), edgeCosts[row][col]));
                }
            }
            
            edges.put(from, edgesForNode);
        }
        
        return edges;
    }
    
    public CompleteGraph subGraph(List<String> labelsToRetain) {
        return new CompleteGraph(this, labelsToRetain);
    }

    @Override
    public String toString() {
        String str = "";
        int maxLabelWidth = findMaxLabelWidth();
        for (int row = 0; row < totalVertices; ++row) {
            str += labels.get(row);
            for (int i=0; i < maxLabelWidth - labels.get(row).length() + 1; ++i) {
                str += " ";
            }
            str += Arrays.toString(edgeCosts[row]) + "\n";
        }
        return str;
    }
    
    

    private void readInLowerHalfMatrix(double[][] lowerHalfCostMatrix) {
        for (int row=totalVertices - 1; row != 0; --row) {
            for (int col=0; col < row; ++col) {
                edgeCosts[row][col] = lowerHalfCostMatrix[row][col];
                edgeCosts[col][row] = lowerHalfCostMatrix[row][col];
            }
        }
    } 

    private int findMaxLabelWidth() {
        int max = 0;
        for (String label : labels) {
            if (label.length() > max) {
                max = label.length();
            }
        }
        return max;
    }
}
