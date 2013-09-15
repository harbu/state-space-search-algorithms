package integrationtests;

import com.google.common.base.CharMatcher;
import org.junit.Before;

import static integrationtests.IntegrationTestHelper.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.bestfirst.IDAStar;
import org.harbu.search.algorithm.Result;
import org.harbu.search.algorithm.bruteforce.BreadthFirstSearch;
import org.harbu.search.algorithm.bruteforce.DepthFirstSearch;
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
    private static final String SOLUTION_FILE = "maze_solution.txt";
    
    private Maze maze;
    private List<Algorithm<MazeState>> algorithmsToRun;
    private int expectedPathLength;

    @Before
    public void setUp() throws IOException {
        maze = new Maze(new MazeInput(readInFile(DATA_FILE)));
        setUpAlgorithms(maze);
        expectedPathLength = CharMatcher.is('X').countIn(readInFile(SOLUTION_FILE));
    }
    
    @Test
    public void testAlgorithms() {
        for (Algorithm algorithm : algorithmsToRun) {
            Result result = algorithm.run();
            assertTrue(result.foundSolution());
            assertEquals(expectedPathLength, result.getDepth());
            System.out.println(result.getStats());
        }
    }

    private void setUpAlgorithms(Maze problem) {
        algorithmsToRun = new ArrayList<>();
        //algorithmsToRun.add(new AStar<>(problem));
        algorithmsToRun.add(new IDAStar<>(problem));
        algorithmsToRun.add(new BreadthFirstSearch<>(problem));
        //algorithmsToRun.add(new DepthFirstSearch<>(problem));
    }
}
