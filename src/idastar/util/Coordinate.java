package idastar.util;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Coordinate)) {
            return false;
        } else {
            Coordinate o = (Coordinate) obj;
            return o.getX() == this.getX() && o.getY() == this.getY();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + x;
        hash = 53 * hash + y;
        return hash;
    }

    public static int manhattanDistance(Coordinate c1, Coordinate c2) {
        int dx = c1.getX() - c2.getX();
        int dy = c1.getY() - c2.getY();
        return Math.abs(dx) + Math.abs(dy);
    }
}
