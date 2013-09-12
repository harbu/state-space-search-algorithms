package org.harbu.search.problem.maze;

import org.harbu.search.problem.Heuristic;
import org.harbu.search.util.Coordinate;

/**
 *
 * @author harbu
 */
public class MazeHeuristic implements Heuristic<MazeState> {

    private Coordinate goalPosition;

    public MazeHeuristic(Coordinate goalPosition) {
        this.goalPosition = goalPosition;
    }

    @Override
    public double calculate(MazeState node) {
        return Coordinate.manhattanDistance(node.getPosition(), goalPosition);
    }
}
