package idastar.problems.slidingpuzzle;

import idastar.problems.Heuristic;
import idastar.util.Coordinate;

public class NPuzzleHeuristic implements Heuristic<NPuzzle> {
    
    private final int n;
    private final NPuzzle goalState;
    private Coordinate[] numberToCoordinate;
    
    public NPuzzleHeuristic(NPuzzle goalState) {
        this.n = goalState.getN();
        this.goalState = goalState;
        this.numberToCoordinate = new Coordinate[16];
        figureOutCoordinatesOfNumbers();
    }
    
    @Override
    public int calculate(NPuzzle state) {
        
        if (state.getN() != n) {
            throw new NPuzzleException("Incompatible puzzle dimensions.");
        }
        
        int sum = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                int number = state.getNumberAtCoordinate(x, y);
                if (number != NPuzzle.EMPTY_SPACE) {
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
