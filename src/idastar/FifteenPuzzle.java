/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harbu
 */
public class FifteenPuzzle {
    
    private static final int EMPTY_SPACE = 0;
    private static final String SIZE_ERROR = "Given array not of size 4x4.";
    private static final String NUMBER_NOT_IN_RANGE =
            "Numbers must be between 1 and 15 or equal to " + EMPTY_SPACE + ".";
    private static final String NUMBER_USED = "Number %d appears more than once.";
    
    
    private final int[][] layout;
    private int emptySpaceY = -1;
    private int emptySpaceX = -1;
    
    public static FifteenPuzzle createFromArray(int[][] initialLayout) {
        verifySize(initialLayout);
        verifyContent(initialLayout);
        return new FifteenPuzzle(initialLayout);
    }
    
    public List<FifteenPuzzle> getMoves() {
        List<FifteenPuzzle> neighbours = new ArrayList<>(4);
        
        if (canSlideDown()) {
            neighbours.add(slideDown());
        }
        
        if (canSlideUp()) {
            neighbours.add(slideUp());
        }
        
        if (canSlideRight()) {
            neighbours.add(slideRight());
        }
        
        if (canSlideLeft()) {
            neighbours.add(slideLeft());
        }
        
        return neighbours;
    }
    
    
    @Override
    public String toString() {
        String output = "";
        for (int[] row : layout) {
            for (int number : row) {
                if (number == EMPTY_SPACE) {
                    output += "  _";
                } else if (number < 10) {
                    output += "  " + number;
                } else {
                    output += " " + number;
                }
            }
            output += "\n";
        }
        return output;
    }
    
    private FifteenPuzzle(int[][] layout) {
        this.layout = layout;
        findCoordinatesOfEmptySpace();
    }

    private static void verifySize(int[][] initialLayout) {
       if (initialLayout.length != 4) {
           throw new RuntimeException(SIZE_ERROR);
       }
       
       for (int[] row : initialLayout) {
           if (row.length != 4) {
              throw new RuntimeException(SIZE_ERROR);
           }
       }
    }

    private static void verifyContent(int[][] initialLayout) {
        boolean[] usedNumbers = new boolean[16];
        
        for (int[] row : initialLayout) {
            for (int number : row) {
                
                if (number != EMPTY_SPACE && ( number < 1 || number > 15)) {
                    throw new RuntimeException(NUMBER_NOT_IN_RANGE);
                }
                
                if (usedNumbers[number]) {
                    throw new RuntimeException(String.format(NUMBER_USED, number));
                }
                
                usedNumbers[number] = true;
            }
        }
    }

    private void findCoordinatesOfEmptySpace() {
        for (int y=0; y < layout.length; ++y) {
            for (int x=0; x < layout[y].length; ++x) {
                if (layout[y][x] == EMPTY_SPACE) {
                    emptySpaceX = x;
                    emptySpaceY = y;
                    return;
                }
            }
        }
    }

    private boolean canSlideDown() {
        return emptySpaceY != 0;
    }

    private boolean canSlideUp() {
        return emptySpaceY != 3;
    }

    private boolean canSlideRight() {
        return emptySpaceX != 0;
    }

    private boolean canSlideLeft() {
        return emptySpaceX != 3;
    }

    private FifteenPuzzle slideDown() {
        int[][] copyOfLayout = makeCopyOfLayout();
        swapWithEmpty(copyOfLayout, emptySpaceX, emptySpaceY - 1);
        return new FifteenPuzzle(copyOfLayout);
    }

    private FifteenPuzzle slideUp() {
        int[][] copyOfLayout = makeCopyOfLayout();
        swapWithEmpty(copyOfLayout, emptySpaceX, emptySpaceY + 1);
        return new FifteenPuzzle(copyOfLayout);
    }

    private FifteenPuzzle slideRight() {
        int[][] copyOfLayout = makeCopyOfLayout();
        swapWithEmpty(copyOfLayout, emptySpaceX - 1, emptySpaceY);
        return new FifteenPuzzle(copyOfLayout);
    }

    private FifteenPuzzle slideLeft() {
        int[][] copyOfLayout = makeCopyOfLayout();
        swapWithEmpty(copyOfLayout, emptySpaceX + 1, emptySpaceY);
        return new FifteenPuzzle(copyOfLayout);
    }

    private int[][] makeCopyOfLayout() {
        int[][] copyOfLayout = new int[4][4];
        
        for (int i=0; i < layout.length; ++i) {
            for (int j=0; j < layout.length; ++j) {
                copyOfLayout[i][j] = layout[i][j];
            }
        }
        
        return copyOfLayout;
    }
    
    private void swapWithEmpty(int[][] newLayout, int destX, int destY) {
        newLayout[emptySpaceY][emptySpaceX] = newLayout[destY][destX];
        newLayout[destY][destX] = EMPTY_SPACE;
    }
}
