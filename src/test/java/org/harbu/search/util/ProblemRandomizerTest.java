package org.harbu.search.util;

import java.util.Arrays;
import org.harbu.search.problem.State;
import org.harbu.search.problem.Operation;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author harbu
 */
public class ProblemRandomizerTest {

    private static final int ROUNDS = 100;
    
    @Test
    public void testRandomizeProblem() {

        State state = mock(State.class);
        when(state.getOperations()).thenReturn(Arrays.asList(new Operation(state, 1)));
        
        ProblemRandomizer.randomizeProblem(state, ROUNDS);
        
        verify(state, times(ROUNDS)).getOperations();

    }
}