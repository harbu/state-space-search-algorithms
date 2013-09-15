package org.harbu.search.problem.explicit;

import java.util.ArrayList;
import java.util.List;
import org.harbu.search.problem.Goal;
import org.harbu.search.util.GraphNode;

/**
 *
 * @author Eric Andrews
 */
public class ExplicitGraphGoal implements Goal<ExplicitGraphState> {
    
    private List<ExplicitGraphState> goalStates;

    public ExplicitGraphGoal(GraphNode ... goalNodes) {
        goalStates = new ArrayList<>();
        for (GraphNode goalNode : goalNodes) {
            goalStates.add(new ExplicitGraphState(goalNode));
        }
    }
    
    @Override
    public boolean isGoalReached(ExplicitGraphState currentState) {
        for (ExplicitGraphState goalState : goalStates) {
            if (goalState.equals(currentState)) {
                return true;
            }
        }
        return false;
    }
    
}
