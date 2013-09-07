package idastar;

import idastar.algorithm.AStar;
import idastar.algorithm.Algorithm;
import idastar.algorithm.IDAStar;
import idastar.problems.slidingpuzzle.NPuzzle;
import idastar.problems.Problem;
import idastar.problems.tsp.TSP;
import idastar.util.CompleteGraph;
import idastar.util.ProblemRandomizer;
import java.util.Arrays;

/**
 *
 * @author harbu
 */
public class Main {
    
    public static void main(String[] args) {
        tsp();
    }

    public static void slidingPuzzle() {
        Problem goal = NPuzzle.createFifteenPuzzle(new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        });

        Problem start = ProblemRandomizer.randomizeProblem(goal);
        Algorithm<NPuzzle> a = new AStar(start, goal);
        a.solve();
        System.out.println(start);
        System.out.println("-----");
        System.out.println(a.getPathToGoal());
        System.out.println("Number of steps required: " + a.getPathToGoal().size());
    }
    
    public static void tsp() {
        CompleteGraph graph = new CompleteGraph(
                Arrays.asList("Turku", "Helsinki", "Vantaa", "Rovaniemi", "Tuusula",
                "Lappeenranta", "Jyväskylä", "Imatra", "Tampere", "Kouvola", "Pietari",
                "Tallinna", "Espoo", "Inkoo"),
                new int[][]{
                    {},
                    {214},
                    {721, 500},
                    {612, 552, 400},
                    {321, 400, 999, 2412},
                    {245, 419, 200, 533, 623},
                    {312, 63, 193, 414, 600, 432},
                    {236, 523, 142, 642, 320, 537, 414},
                    {415, 500, 301, 536, 699, 234, 52, 164},
                    {461, 900, 1256, 300, 532, 700, 63, 193, 613},
                    {1725, 519, 5156, 491, 234, 600, 925, 651, 531, 692},
                    {145, 623, 90, 124, 367, 629, 125, 731, 259, 672, 156, 953},
                    {942, 532, 125, 631, 356, 124, 63, 215, 631, 564, 619, 124, 532},
                    {145, 623, 500, 425, 623, 532, 319, 59, 523, 699, 958, 5122, 259, 637}
                    
                }
        );
        
        Problem start = TSP.makeTSPStart(graph, "Vantaa");
        Problem goal = TSP.makeTSPGoal(graph, "Vantaa");
        Algorithm<TSP> a = new IDAStar(start, goal);
        a.solve();
        System.out.println(start);
        System.out.println("-----");
        System.out.println(a.getPathToGoal());
        System.out.println("Number of steps required: " + a.getPathToGoal().size());
    }
}
