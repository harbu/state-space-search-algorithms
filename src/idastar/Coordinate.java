/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar;

/**
 *
 * @author harbu
 */
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
    
    public static int manhattanDistance(Coordinate c1, Coordinate c2) {
        int dx = c1.getX() - c2.getX();
        int dy = c1.getY() - c2.getY();
        return Math.abs(dx) + Math.abs(dy);
    }
}
