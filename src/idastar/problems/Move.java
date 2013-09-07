package idastar.problems;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Move)) {
            return false;
        } else {
            Move other = (Move) obj;
            return this.getNode().equals(other.getNode())
                    && this.getCost() == other.getCost();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.node);
        hash = 47 * hash + this.cost;
        return hash;
    }
    
    
}
