package idastar.problems;

public class Move<T extends Problem<T>> {

    private final T node;
    private final int cost;

    public Move(T node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public T getNode() {
        return node;
    }

    public int getCost() {
        return cost;
    }
}
