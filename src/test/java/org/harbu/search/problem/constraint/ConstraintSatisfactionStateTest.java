package org.harbu.search.problem.constraint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.harbu.search.problem.Operation;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

public class ConstraintSatisfactionStateTest {

    private ConstraintSatisfactionState state;

    @Before
    public void setUp() {
        Set<Integer> domain = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        state = new ConstraintSatisfactionState(3, domain, new Constraint() {
            @Override
            public void apply(int varIndex, int valueChosen, List<Set<Integer>> domains) {
                for (Set<Integer> domain : domains) {
                    domain.remove(valueChosen);
                    domain.remove(6 - valueChosen);

                    domain.remove(valueChosen % 2);
                    domain.remove(valueChosen % 3);

                    domain.remove(varIndex + 1);
                    domain.remove(6 - varIndex);

                    domain.remove(valueChosen + varIndex);
                    domain.remove(valueChosen - varIndex);
                }
            }
        });
    }

    @Test
    public void testToString() {
        String expected = "Variable 0: 1\n"
                + "Variable 1: [2, 3, 4]\n"
                + "Variable 2: [2, 3, 4]\n";

        assertEquals(expected, doSteps(state, 1).toString());
    }

    @Test
    public void testGetOperations() {

        List<String> expectedStates = Arrays.asList(
                "Variable 0: 1\n"
                + "Variable 1: [2, 3, 4]\n"
                + "Variable 2: [2, 3, 4]\n",
                "Variable 0: 2\n"
                + "Variable 1: [3, 5]\n"
                + "Variable 2: [3, 5]\n",
                "Variable 0: 3\n"
                + "Variable 1: [2, 4, 5]\n"
                + "Variable 2: [2, 4, 5]\n",
                "Variable 0: 4\n"
                + "Variable 1: [3, 5]\n"
                + "Variable 2: [3, 5]\n",
                "Variable 0: 5\n"
                + "Variable 1: [3, 4]\n"
                + "Variable 2: [3, 4]\n",
                "Variable 0: 6\n"
                + "Variable 1: [2, 3, 4, 5]\n"
                + "Variable 2: [2, 3, 4, 5]\n");

        List<Operation<ConstraintSatisfactionState>> operations = state.getOperations();


        assertEquals(6, operations.size());

        for (int i = 0; i < operations.size(); ++i) {
            assertEquals(expectedStates.get(i), operations.get(i).getNode().toString());
            assertEquals(1, operations.get(i).getCost(), 0.00000001);
        }

    }

    @Test
    public void testGetVariables() {
        Integer[] expected = new Integer[]{1, 2, null};
        assertArrayEquals(expected, doSteps(state, 2).getVariables());
    }

    @Test
    public void testEquals_false() {
        assertFalse(state.equals(doSteps(state, 2)));
    }

    @Test
    public void testEquals_true() {
        assertTrue(doSteps(state, 2).equals(doSteps(state, 2)));
    }
}