package org.harbu.search.problem.explicit;

import org.harbu.search.problem.NoHeuristic;
import org.harbu.search.problem.Problem;
import org.harbu.search.util.GraphNode;

/**
 *
 * @author Eric Andrews
 */
public class ExplicitGraph extends Problem<ExplicitGraphState> {

    public ExplicitGraph(GraphNode start, GraphNode ... goals) {
        setStartState(new ExplicitGraphState(start));
        setGoal(new ExplicitGraphGoal(goals));
        setHeuristic(new NoHeuristic<ExplicitGraphState>());
    }
}
