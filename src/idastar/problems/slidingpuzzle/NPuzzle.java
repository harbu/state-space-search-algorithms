package idastar.problems.slidingpuzzle;

import idastar.problems.Heuristic;
import idastar.problems.Move;
import idastar.problems.Problem;
import idastar.util.Coordinate;
import idastar.util.TwoDArrayUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a classical sliding puzzle.
 *
 * @author Eric Andrews
 */
public class NPuzzle implements Problem<NPuzzle> {

    public static final int COST_TO_MOVE = 1;
    public static final int EMPTY_SPACE = 0;
    private final int n;
    private final int[][] layout;
    private Coordinate emptySpace;

    public static NPuzzle createFifteenPuzzle(int[][] initialLayout) {
        NPuzzleInputVerifier.verify(initialLayout, 4);
        return new NPuzzle(initialLayout, 4);
    }

    public static NPuzzle createEightPuzzle(int[][] initialLayout) {
        NPuzzleInputVerifier.verify(initialLayout, 3);
        return new NPuzzle(initialLayout, 3);
    }

    @Override
    public List<Move<NPuzzle>> getMoves() {
        List<NPuzzle> neighbours = new ArrayList<>(4);

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

        List<Move<NPuzzle>> moves = new ArrayList<>(neighbours.size());
        for (NPuzzle node : neighbours) {
            moves.add(new Move<>(node, COST_TO_MOVE));
        }
        return moves;
    }

    public int getNumberAtCoordinate(int x, int y) {
        return layout[y][x];
    }

    public int getN() {
        return n;
    }
    
    @Override
    public Heuristic<NPuzzle> createHeuristicToThisNode() {
        return new NPuzzleHeuristic(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof NPuzzle)) {
            return false;
        }

        NPuzzle other = (NPuzzle) o;

        if (this.n != other.n) {
            return false;
        }

        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (this.getNumberAtCoordinate(x, y) != other.getNumberAtCoordinate(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.n;
        hash = 29 * hash + Arrays.deepHashCode(this.layout);
        hash = 29 * hash + Objects.hashCode(this.emptySpace);
        return hash;
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


    private NPuzzle(int[][] layout, int n) {
        this.n = n;
        this.layout = layout;
        findCoordinatesOfEmptySpace();
    }

    private void findCoordinatesOfEmptySpace() {
        emptySpace = TwoDArrayUtils.findValue(layout, EMPTY_SPACE);
    }

    private boolean canSlideDown() {
        return emptySpace.getY() != 0;
    }

    private boolean canSlideUp() {
        return emptySpace.getY() != (n - 1);
    }

    private boolean canSlideRight() {
        return emptySpace.getX() != 0;
    }

    private boolean canSlideLeft() {
        return emptySpace.getX() != (n - 1);
    }

    private NPuzzle slideDown() {
        int[][] copyOfLayout = TwoDArrayUtils.makeCopy(layout);
        swapWithEmpty(copyOfLayout, emptySpace.getX(), emptySpace.getY() - 1);
        return new NPuzzle(copyOfLayout, n);
    }

    private NPuzzle slideUp() {
        int[][] copyOfLayout = TwoDArrayUtils.makeCopy(layout);
        swapWithEmpty(copyOfLayout, emptySpace.getX(), emptySpace.getY() + 1);
        return new NPuzzle(copyOfLayout, n);
    }

    private NPuzzle slideRight() {
        int[][] copyOfLayout = TwoDArrayUtils.makeCopy(layout);
        swapWithEmpty(copyOfLayout, emptySpace.getX() - 1, emptySpace.getY());
        return new NPuzzle(copyOfLayout, n);
    }

    private NPuzzle slideLeft() {
        int[][] copyOfLayout = TwoDArrayUtils.makeCopy(layout);
        swapWithEmpty(copyOfLayout, emptySpace.getX() + 1, emptySpace.getY());
        return new NPuzzle(copyOfLayout, n);
    }

    private void swapWithEmpty(int[][] newLayout, int destX, int destY) {
        newLayout[emptySpace.getY()][emptySpace.getX()] = newLayout[destY][destX];
        newLayout[destY][destX] = EMPTY_SPACE;
    }
}
