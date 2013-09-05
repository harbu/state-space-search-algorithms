package idastar;

/**
 *
 * @author harbu
 */
public class FifteenPuzzleHeuristic {
    
    private final FifteenPuzzle goalState;
    private Coordinate[] numberToCoordinate;
    
    private FifteenPuzzleHeuristic(FifteenPuzzle goalState) {
        this.goalState = goalState;
        figureOutCoordinatesOfNumbers();
    }
    
    public static FifteenPuzzleHeuristic initializeWithGoal(FifteenPuzzle goalState) {
        return new FifteenPuzzleHeuristic(goalState);
    }
    
    public int calculate(FifteenPuzzle state) {
        int sum = 0;
        
        for (int y=0; y < 4; ++y) {
            for (int x=0; x < 4; ++x) {
                int number = state.getNumberAtCoordinate(x, y);
                Coordinate currCoord = new Coordinate(x, y);
                Coordinate goalCoord = numberToCoordinate[number];
                sum += Coordinate.manhattanDistance(currCoord, goalCoord);
            }
        }
        
        return sum;
    }

    private void figureOutCoordinatesOfNumbers() {
        numberToCoordinate = new Coordinate[16];
        
        for (int y=0; y < 4; ++y) {
            for (int x=0; x < 4; ++x) {
                int number = goalState.getNumberAtCoordinate(x, y);
                numberToCoordinate[number] = new Coordinate(x, y);
            }
        }
    }
}
