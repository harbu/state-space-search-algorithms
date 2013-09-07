package idastar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    public int cost(String fromLabel, String toLabel) {
        int from = labels.indexOf(fromLabel);
        int to = labels.indexOf(toLabel);
        return edgeCosts[from][to];
    }
    
    public List<String> getNodeLabels() {
        return new ArrayList<>(labels);
    }

    private void readInLowerHalfMatrix(int[][] upperHalfCostMatrix) {
        for (int row=upperHalfCostMatrix.length-1; row != 0; --row) {
            for (int col=0; col < upperHalfCostMatrix[row].length; ++col) {
                edgeCosts[row][col] = upperHalfCostMatrix[row][col];
                edgeCosts[col][row] = upperHalfCostMatrix[row][col];
            }
        }
        for (int[] row : edgeCosts) {
            System.out.println(Arrays.toString(row));
        }
    } 
}
