package org.harbu.search.problem.explicit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.State;
import org.harbu.search.util.GraphNode;

/**
 *
 * @author Eric Andrews
 */
public class ExplicitGraphState implements State<ExplicitGraphState> {

    private GraphNode currentNode;

    public ExplicitGraphState(GraphNode startNode) {
        this.currentNode = startNode;
    }

    @Override
    public List<Operation<ExplicitGraphState>> getOperations() {
        List<Operation<ExplicitGraphState>> operations = new ArrayList<>();
        for (Entry<GraphNode, Double> entry : currentNode.getChildren().entrySet()) {
            ExplicitGraphState neighbor = new ExplicitGraphState(entry.getKey());
            operations.add(new Operation<>(neighbor, entry.getValue()));
        }
        return operations;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ExplicitGraphState)) {
            return false;
        } else {
            ExplicitGraphState o = (ExplicitGraphState) obj;
            return this.currentNode == o.currentNode;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.currentNode);
        return hash;
    }

    @Override
    public String toString() {
        return "Node " + currentNode.getIdentifier() + "\n";
    }
}
