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
public class NQueensStateTest {
    
    private ConstraintSatisfactionState state;
    
    @Before
    public void setUp() {
        state = new NQueensState(4);
    }

    @Test
    public void testToString() {
        String expected = "QXXX\n"
                + "XXQX\n"
                + "XXXX\n"
                + "XXXX\n";
        assertEquals(expected, doSteps(state, 2).toString());
    }
    
    @Test
    public void testGetOperations() {
        String template = "XXXX\nXXXX\nXXXX\nXXXX\n";
        Set<String> expected = new HashSet<>(Arrays.asList(
                "Q" + template.substring(1),
                "XQ" + template.substring(2),
                "XXQ" + template.substring(3),
                "XXXQ" + template.substring(4)));
        
        Set<String> actualStates = new HashSet<>();
        for (Operation operation : state.getOperations()) {
            actualStates.add(operation.getNode().toString());
        }
        
        assertEquals(expected, actualStates);
    }
}