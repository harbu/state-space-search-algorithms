package integrationtests;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.harbu.search.algorithm.AStar;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.IDAStar;
import org.harbu.search.problem.tsp.TSP;
import org.harbu.search.problem.tsp.TSPCoordinateReader;
import org.harbu.search.problem.tsp.TSPState;
import org.harbu.search.util.CompleteGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class TSPIT {
    
    private static final String DATA_FILE = "wi29.tsp";
    
    private List<Algorithm<TSPState>> algorithms;
    
    @Before
    public void setUp() throws IOException {
        String input = readInFileAndCutOutMetaData();
        CompleteGraph graph = TSPCoordinateReader.readCoordinateString(input);
        TSPState start = TSPState.makeTSPStart(graph, "1");
        algorithms = setUpAlgorithms(new TSP(start));
    }
    
    @Test
    public void testAlgorithms() {
        for (Algorithm algorithm : algorithms) {
            assertTrue(algorithm.solve());
        }
    }
    
    private static String readInFileAndCutOutMetaData() throws IOException {
        URL dataFile = Resources.getResource(DATA_FILE);
        String data = Resources.toString(dataFile, Charsets.UTF_8);
        int cutOffIndex = data.indexOf("NODE_COORD_SECTION")
                +"NODE_COORD_SECTION\n".length();
        return data.substring(cutOffIndex);
    }
    
    
    private List<Algorithm<TSPState>> setUpAlgorithms(TSP problem) {
        List<Algorithm<TSPState>> algorithmsToRun = new ArrayList<>();
        algorithmsToRun.add(new IDAStar<>(problem));
        return algorithmsToRun;
    }
}