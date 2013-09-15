package org.harbu.search.algorithm.bruteforce;

import org.harbu.search.util.GraphNode;
import org.junit.Before;

/**
 *
 * @author Eric Andrews
 */
public abstract class BruteForceSearchTest {
    protected GraphNode root;
    protected GraphNode c;
    protected GraphNode aaaa;

    @Before
    public void setUp() {
        root = new GraphNode("root");

        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        c = new GraphNode("c");
        root.addChildren(a, b, c);

        GraphNode aa = new GraphNode("aa");
        GraphNode ab = new GraphNode("ab");
        a.addChildren(aa, ab);

        GraphNode ba = new GraphNode("ba");
        GraphNode bb = new GraphNode("bb");
        GraphNode bc = new GraphNode("bc");
        b.addChildren(ba, bb, bc);

        GraphNode ca = new GraphNode("ca");
        c.addChildren(ca);

        GraphNode aaa = new GraphNode("aaa");
        aa.addChildren(aaa);

        GraphNode caa = new GraphNode("caa");
        GraphNode cab = new GraphNode("cab");
        ca.addChildren(caa, cab);

        aaaa = new GraphNode("aaaa");
        aa.addChildren(aaaa);
    }
}
