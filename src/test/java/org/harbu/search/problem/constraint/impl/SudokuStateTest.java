package org.harbu.search.problem.constraint.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.constraint.ConstraintSatisfactionState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.harbu.search.test.TestHelper.*;

/**
 *
 * @author harbu
 */
public class SudokuStateTest {

    private ConstraintSatisfactionState state;

    @Before
    public void setUp() {
        state = new SudokuState(new int[9][9]);
    }

    @Test
    public void testToString() {
        String expected = "|1 2 3|4 5 6|7 8 9|\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n";

        assertEquals(expected, doSteps(state, 9).toString());
    }

    @Test
    public void testGetOperations() {
        String template = "|X    |     |     |\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n"
                + "|     |     |     |\n";

        Set<String> expectedStates = new HashSet<>(Arrays.asList(
                template.replace("X", "1"),
                template.replace("X", "2"),
                template.replace("X", "3"),
                template.replace("X", "4"),
                template.replace("X", "5"),
                template.replace("X", "6"),
                template.replace("X", "7"),
                template.replace("X", "8"),
                template.replace("X", "9")));

        Set<String> actualStates = new HashSet<>();
        for (Operation operation : state.getOperations()) {
            actualStates.add(operation.getNode().toString());
        }
        
        assertEquals(expectedStates, actualStates);
    }
}