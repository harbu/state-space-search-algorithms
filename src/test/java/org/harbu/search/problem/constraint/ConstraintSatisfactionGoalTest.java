package org.harbu.search.problem.constraint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

public class ConstraintSatisfactionGoalTest {

    private ConstraintSatisfactionGoal goal;
    private ConstraintSatisfactionState state;

    @Before
    public void setUp() {
        Set<Integer> domain = new HashSet<>();
        domain.add(666);
        state = new ConstraintSatisfactionState(3, domain, new Constraint() {
            @Override
            public void apply(int varIndex, int valueChosen, List<Set<Integer>> domains) {
            }
        });
        goal = new ConstraintSatisfactionGoal();
    }

    @Test
    public void testIsGoalReached_false() {
        assertFalse(goal.isGoalReached(state));
    }

    @Test
    public void testIsGoalReached_true() {
        assertTrue(goal.isGoalReached(doSteps(state, 3)));
    }
}