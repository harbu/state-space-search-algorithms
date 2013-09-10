package idastar.problems;

import java.util.Objects;

public class Operation<T extends State<T>> {

    private final T node;
    private final int cost;

    public Operation(T node, int cost) {
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
        if (obj == null || !(obj instanceof Operation)) {
            return false;
        } else {
            Operation other = (Operation) obj;
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
