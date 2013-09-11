package org.harbu.search.algorithm;

import java.util.List;
import org.harbu.search.problem.Operation;
import org.harbu.search.problem.State;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class QueueEntryTest {

    private static final double DELTA = 0.0001;
    private QueueEntry<ExampleState> queueEntry;

    @Before
    public void setUp() {
        queueEntry = new QueueEntry<>(new ExampleState(), 3.141592, 9);
    }

    @Test
    public void testGetF() {
        assertEquals(9 + 3.141592, queueEntry.getF(), DELTA);
    }

    @Test
    public void testCompareTo() {
        assertEquals(-1, queueEntry.compareTo(new QueueEntry<>(new ExampleState(), 6.141592, 8)));
        assertEquals(1, queueEntry.compareTo(new QueueEntry<>(new ExampleState(), 0, 0)));
    }

    private class ExampleState implements State<ExampleState> {

        @Override
        public List<Operation<ExampleState>> getOperations() {
            return null;
        }
    }
}