package integrationtests;

import org.junit.Before;

import static integrationtests.IntegrationTestHelper.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.harbu.search.algorithm.AStar;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.BruteForceSearch;
import static org.harbu.search.algorithm.BruteForceSearch.SearchType.BREADTH_FIRST;
import static org.harbu.search.algorithm.BruteForceSearch.SearchType.DEPTH_FIRST;
import org.harbu.search.algorithm.IDAStar;
import org.harbu.search.problem.maze.Maze;
import org.harbu.search.problem.maze.MazeInput;
import org.harbu.search.problem.maze.MazeState;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harbu
 */
public class MazeIt {

    private static final String DATA_FILE = "maze.txt";
    
    private Maze maze;
    private List<Algorithm<MazeState>> algorithmsToRun;

    @Before
    public void setUp() throws IOException {
        maze = new Maze(new MazeInput(readInFile(DATA_FILE)));
        setUpAlgorithms(maze);
    }
    
    @Test
    public void testAlgorithms() {
        for (Algorithm algorithm : algorithmsToRun) {
            assertTrue(algorithm.solve());
        }
    }

    private void setUpAlgorithms(Maze problem) {
        algorithmsToRun = new ArrayList<>();
        algorithmsToRun.add(new AStar<>(problem));
        algorithmsToRun.add(new IDAStar<>(problem));
        algorithmsToRun.add(new BruteForceSearch<>(problem, DEPTH_FIRST));
        algorithmsToRun.add(new BruteForceSearch<>(problem, BREADTH_FIRST));
    }
}
