package org.harbu.search.problem.maze;

import org.harbu.search.util.Coordinate;

/**
 *
 * @author harbu
 */
public class MazeInput {
    
    public static final char START_TILE = 'S';
    public static final char GOAL_TILE = 'G';
    public static final char FLOOR_TILE = ' ';
    public static final char WALL_TILE = '#';
    
    private int[][] layout;
    private Coordinate startPosition;
    private Coordinate goalPosition;
    
    public MazeInput(String input) {
        parseInput(input);
        if (startPosition == null || goalPosition == null) {
            throw new RuntimeException("Start or goal tile missing.");
        }
    }

    public int[][] getLayout() {
        return layout;
    }

    public Coordinate getStartPosition() {
        return startPosition;
    }

    public Coordinate getGoalPosition() {
        return goalPosition;
    }

    private void parseInput(String input) {
        String[] rows = input.split("\\n");
        int height = rows.length;
        
        layout = new int[height][];
        
        for (int i=0; i < height; ++i) {
            int width = rows[i].length();
            layout[i] = new int[width];
            
            for (int j=0; j < width; ++j) {
                layout[i][j] = parseCharacter(rows[i].charAt(j), j, i);
            }
        }
    }
    
    private int parseCharacter(char ch, int x, int y) {
        if (ch == WALL_TILE) {
            return MazeState.WALL;
        } else {
            if (ch == START_TILE) {
                return handleStart(x, y);
            } else if (ch == GOAL_TILE) {
                return handleGoal(x, y);
            } else if (ch == FLOOR_TILE) {
                return MazeState.FLOOR;
            } else {
                String msg = String.format("Character %c at (%d, %d) has no meaning.", ch, x, y);
                throw new RuntimeException(msg);
            }
        }
    }

    private int handleStart(int x, int y) {
        if (startPosition == null) {
            startPosition = new Coordinate(x, y);
            return MazeState.FLOOR;
        } else {
            String msg = "More than one start position defined.";
            throw new RuntimeException(msg);
        }
    }

    private int handleGoal(int x, int y) {
         if (goalPosition == null) {
            goalPosition = new Coordinate(x, y);
            return MazeState.FLOOR;
        } else {
            String msg = "More than one goal position defined.";
            throw new RuntimeException(msg);
        }
    }
}
