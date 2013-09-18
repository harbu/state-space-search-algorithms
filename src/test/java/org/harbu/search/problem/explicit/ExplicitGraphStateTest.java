package org.harbu.search.problem.explicit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.harbu.search.problem.Operation;
import org.harbu.search.util.GraphNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric Andrews
 */
public class ExplicitGraphStateTest {

    private GraphNode a;
    private GraphNode b;
    private GraphNode c;
    private ExplicitGraphState state;

    @Before
    public void setUp() {
        GraphNode root = new GraphNode("root");
        a = new GraphNode("root");
        b = new GraphNode("root");
        c = new GraphNode("root");
        root.addChildren(a, b, c);
        state = new ExplicitGraphState(root);
    }

    @Test
    public void testToString() {
        assertEquals("Node root\n", state.toString());
    }

    @Test
    public void testGetOperations() {
        Set<Operation<ExplicitGraphState>> expected = new HashSet<>(Arrays.asList(
                new Operation<>(new ExplicitGraphState(a), 1),
                new Operation<>(new ExplicitGraphState(b), 1),
                new Operation<>(new ExplicitGraphState(c), 1)));
        assertEquals(expected, new HashSet<>(state.getOperations()));
    }

    @Test
    public void testEquals_false() {
        assertFalse(state.equals(new ExplicitGraphState(a)));
    }

    @Test
    public void testEquals_true() {
        ExplicitGraphState childA = state.getOperations().get(0).getNode();
        assertTrue(childA.equals(new ExplicitGraphState(a)));
    }
}