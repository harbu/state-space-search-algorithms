package org.harbu.search.problem.maze;

import org.harbu.search.problem.Problem;

/**
 * An instance of a maze, where we try to find the shortest path from the start
 * position to the goal position. In this implementation, each edge has unit
 * cost.
 *
 * @author Eric Andrews
 */
public class Maze extends Problem<MazeState> {

    public Maze(MazeInput input) {
        setStartState(new MazeState(input.getLayout(), input.getStartPosition()));
        setGoal(new MazeGoal(input.getGoalPosition()));
        setHeuristic(new MazeHeuristic(input.getGoalPosition()));
    }
}
