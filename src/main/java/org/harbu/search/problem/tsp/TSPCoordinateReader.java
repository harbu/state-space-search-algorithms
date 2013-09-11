package org.harbu.search.problem.tsp;

import java.util.ArrayList;
import java.util.List;
import org.harbu.search.util.CompleteGraph;
import org.harbu.search.util.Coordinate;

/**
 * Read in coordinate input to build a graph.
 * @author Eric Andrews
 */
public class TSPCoordinateReader {
    
    private static final int NAME_INDEX = 0;
    private static final int X_INDEX = 1;
    private static final int Y_INDEX=  2;
    
    public static CompleteGraph readCoordinateString(String input) {
        List<String> vertexLabels = new ArrayList<>();
        List<Coordinate> vertexCoordinates = new ArrayList<>();
        
        for (String line : input.split("\\n")) {
            String[] words = line.split("\\s+");
            if (words.length != 3) {
                throw new RuntimeException("Should have 3 entries per line");
            }
            
            String label = words[NAME_INDEX];
            int x = (int) Double.parseDouble(words[X_INDEX]);
            int y = (int) Double.parseDouble(words[Y_INDEX]);
            
            vertexLabels.add(label);
            vertexCoordinates.add(new Coordinate(x, y));
        }
        
        int[][] distances = computePairWiseDistances(vertexCoordinates);
        return new CompleteGraph(vertexLabels, distances);
    }

    private static int[][] computePairWiseDistances(List<Coordinate> vertexCoordinates) {
        int n = vertexCoordinates.size();
        int[][] lowerHalfMatrix = new int[n][];
        for (int i=n-1; i > 0; --i) {
            int[] distances = new int[i];
            
            Coordinate c1 = vertexCoordinates.get(i);
            
            for (int j=0; j < i; ++j) {
                Coordinate c2 = vertexCoordinates.get(j);
                distances[j] = (int) Coordinate.euclideanDistance(c1, c2);
            }
            
            lowerHalfMatrix[i] = distances;
        }
        return lowerHalfMatrix;
    }
}
