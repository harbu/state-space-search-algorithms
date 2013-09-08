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

    public CompleteGraph(List<String> labels, int[][] lowerHalfCostMatrix) {
        this.labels = labels;
        this.edgeCosts = new int[labels.size()][labels.size()];
        readInLowerHalfMatrix(lowerHalfCostMatrix);
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
    
    public Map<String, Set<Edge>> getEdges() {
        Map<String, Set<Edge>> edges = new HashMap<>();
        
        for (int row=0; row < labels.size(); ++row) {
            String from = labels.get(row);
            Set<Edge> edgesForNode = new HashSet<>();
            
            for (int col=0; col < labels.size(); ++col) {
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
        for (int row = 0; row < labels.size(); ++row) {
            str += labels.get(row);
            for (int i=0; i < maxLabelWidth - labels.get(row).length() + 1; ++i) {
                str += " ";
            }
            str += Arrays.toString(edgeCosts[row]) + "\n";
        }
        return str;
    }
    
    

    private void readInLowerHalfMatrix(int[][] upperHalfCostMatrix) {
        for (int row=labels.size() - 1; row != 0; --row) {
            for (int col=0; col < row; ++col) {
                edgeCosts[row][col] = upperHalfCostMatrix[row][col];
                edgeCosts[col][row] = upperHalfCostMatrix[row][col];
            }
        }
        System.out.println(this);
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
