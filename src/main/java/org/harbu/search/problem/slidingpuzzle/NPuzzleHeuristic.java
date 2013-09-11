package org.harbu.search.problem.slidingpuzzle;

import org.harbu.search.problem.Heuristic;
import org.harbu.search.util.Coordinate;

public class NPuzzleHeuristic implements Heuristic<NPuzzleState> {
    
    private final int n;
    private final NPuzzleState goalState;
    private Coordinate[] numberToCoordinate;
    
    public NPuzzleHeuristic(NPuzzleState goalState) {
        this.n = goalState.getN();
        this.goalState = goalState;
        this.numberToCoordinate = new Coordinate[16];
        figureOutCoordinatesOfNumbers();
    }
    
    @Override
    public double calculate(NPuzzleState state) {
        
        if (state.getN() != n) {
            throw new NPuzzleException("Incompatible puzzle dimensions.");
        }
        
        int sum = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                int number = state.getNumberAtCoordinate(x, y);
                if (number != NPuzzleState.EMPTY_SPACE) {
                    Coordinate coord = new Coordinate(x, y);
                    Coordinate goalCoord = numberToCoordinate[number];
                    sum += Coordinate.manhattanDistance(coord, goalCoord);
                }
            }
        }
        return sum;
    }
    
    private void figureOutCoordinatesOfNumbers() {
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                int number = goalState.getNumberAtCoordinate(x, y);
                numberToCoordinate[number] = new Coordinate(x, y);
            }
        }
    }
}
