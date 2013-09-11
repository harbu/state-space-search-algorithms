package org.harbu.search.problem.maze;

import org.harbu.search.problem.Problem;

/**
 *
 * @author harbu
 */
public class Maze extends Problem<MazeState> {

    public Maze(MazeInput input) {
        setStartState(new MazeState(input.getLayout(), input.getStartPosition()));
        setGoal(new MazeGoal(input.getGoalPosition()));
        setHeuristic(new MazeHeuristic(input.getGoalPosition()));
    }
}
