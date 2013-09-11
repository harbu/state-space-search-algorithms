package org.harbu.search.problem.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.State;
import org.harbu.search.util.Coordinate;
import org.harbu.search.util.TwoDArrayUtils;

/**
 *
 * @author harbu
 */
public class MazeState implements State<MazeState> {

    public static final int WALL = 1;
    public static final int FLOOR = 0;
    
    private int[][] layout;
    private Coordinate position;

    public MazeState(int[][] layout, Coordinate startPosition) {
        this.layout = layout;
        this.position = startPosition;
    }

    private MazeState(MazeState previous, Coordinate newPosition) {
        this.layout = previous.layout;
        this.position = newPosition;
    }

    public Coordinate getPosition() {
        return position;
    }

    @Override
    public List<Operation<MazeState>> getOperations() {
        List<Operation<MazeState>> moves = new ArrayList<>(4);
        for (Coordinate nextPosition : neighbours()) {
            moves.add(new Operation<>(new MazeState(this, nextPosition), 1));
        }
        return moves;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MazeState)) {
            return false;
        } else {
            MazeState o = (MazeState) obj;
            return this.layout == o.layout && this.position.equals(o.position);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Arrays.hashCode(this.layout);
        hash = 31 * hash + Objects.hashCode(this.position);
        return hash;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i=0; i < layout.length; ++i) {
            for (int j=0; j < layout[i].length; ++j) {
                if (position.getX() == j && position.getY() == i) {
                    str += MazeInput.START_TILE;
                } else if (layout[i][j] == FLOOR) {
                    str += MazeInput.FLOOR_TILE;
                } else {
                    str += MazeInput.WALL_TILE;
                }
            }
            str += "\n";
        }
        return str;
    }

    private List<Coordinate> neighbours() {
        List<Coordinate> coordinates = new ArrayList<>(4);
        if (canGoLeft()) {
            coordinates.add(position.left());
        }
        if (canGoRight()) {
            coordinates.add(position.right());
        }
        if (canGoUp()) {
            coordinates.add(position.up());
        }
        if (canGoDown()) {
            coordinates.add(position.down());
        }
        return coordinates;
    }

    private boolean canGoLeft() {
        return position.getX() > 0
                && TwoDArrayUtils.getValueAt(position.left(), layout) == FLOOR;
    }

    private boolean canGoRight() {
        return position.getX() < layout[(int) position.getY()].length - 1
                && TwoDArrayUtils.getValueAt(position.right(), layout) == FLOOR;
    }

    private boolean canGoUp() {
        return position.getY() > 0
                && position.getX() < layout[(int) position.getY() - 1].length
                && TwoDArrayUtils.getValueAt(position.up(), layout) == FLOOR;
    }

    private boolean canGoDown() {
        return position.getY() < layout.length - 1
                && position.getX() < layout[(int) position.getY() + 1].length
                && TwoDArrayUtils.getValueAt(position.down(), layout) == FLOOR;
    }
}
