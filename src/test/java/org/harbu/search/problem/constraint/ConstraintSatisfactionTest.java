package org.harbu.search.problem.constraint;

import org.harbu.search.problem.NoHeuristic;
import org.harbu.search.problem.constraint.impl.NQueensState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConstraintSatisfactionTest {

    private ConstraintSatisfactionState state;
    private ConstraintSatisfaction problem;

    @Before
    public void setUp() {
        state = new NQueensState(4);
        problem = new ConstraintSatisfaction(state);
    }

    @Test
    public void testVarsSet() {
        assertSame(problem.getStartState(), state);
        assertEquals(ConstraintSatisfactionGoal.class, problem.getGoal().getClass());
        assertEquals(NoHeuristic.class, problem.getHeuristic().getClass());
    }
}