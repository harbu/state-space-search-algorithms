package org.harbu.search.problem.maze;

import org.harbu.search.problem.Goal;
import org.harbu.search.util.Coordinate;

/**
 *
 * @author harbu
 */
public class MazeGoal implements Goal<MazeState> {

    private final Coordinate goalPosition;

    public MazeGoal(Coordinate goalPosition) {
        this.goalPosition = goalPosition;
    }

    @Override
    public boolean isGoalReached(MazeState currentState) {
        return goalPosition.equals(currentState.getPosition());
    }
}
