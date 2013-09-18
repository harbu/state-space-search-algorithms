package org.harbu.search.problem.explicit;

import org.harbu.search.util.GraphNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExplicitGraphGoalTest {

    private ExplicitGraphState state;
    private ExplicitGraphGoal goal;

    @Before
    public void setUp() {
        GraphNode rootNode = new GraphNode("root");
        GraphNode goalNode = new GraphNode("goal");
        rootNode.addChild(goalNode, 1);

        state = new ExplicitGraphState(rootNode);
        goal = new ExplicitGraphGoal(goalNode);
    }

    @Test
    public void testIsGoalReached_false() {
        assertFalse(goal.isGoalReached(state));
    }

    @Test
    public void testIsGoalReached_true() {
        assertTrue(goal.isGoalReached(state.getOperations().get(0).getNode()));
    }
}