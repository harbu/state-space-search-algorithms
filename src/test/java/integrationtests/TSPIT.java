package integrationtests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.harbu.search.algorithm.bestfirst.AStar;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.problem.tsp.TSP;
import org.harbu.search.problem.tsp.TSPCoordinateReader;
import org.harbu.search.problem.tsp.TSPState;
import org.harbu.search.util.CompleteGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static integrationtests.IntegrationTestHelper.*;
import org.harbu.search.algorithm.bestfirst.DFBnB;
import org.harbu.search.algorithm.Result;
import org.harbu.search.algorithm.bestfirst.RecursiveBestFirst;

/**
 *
 * @author harbu
 */
public class TSPIT {
    
    private static final String START_LABEL = "A";
    private static final String DATA_FILE = "tsp10.txt";
    
    private List<Algorithm<TSPState>> algorithms;
    
    @Before
    public void setUp() throws IOException {
        String input = readInFile(DATA_FILE);
        CompleteGraph graph = TSPCoordinateReader.readCoordinateString(input);
        TSPState start = TSPState.makeTSPStart(graph, START_LABEL);
        algorithms = setUpAlgorithms(new TSP(start));
    }
    
    @Test
    public void testAlgorithms() {
        for (Algorithm algorithm : algorithms) {
            Result result = algorithm.run();
            assertTrue(result.foundSolution());
            assertEquals(1552.9612081934351, result.getTotalCost(), 0.00001);
            System.out.println(result.getStats());
        }
    }
    
    
    private List<Algorithm<TSPState>> setUpAlgorithms(TSP problem) {
        List<Algorithm<TSPState>> algorithmsToRun = new ArrayList<>();
        algorithmsToRun.add(new AStar<>(problem));
        algorithmsToRun.add(new DFBnB<>(problem));
        algorithmsToRun.add(new RecursiveBestFirst<>(problem));
        return algorithmsToRun;
    }
}