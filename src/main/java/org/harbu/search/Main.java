package org.harbu.search;

import org.harbu.search.algorithm.AStar;
import org.harbu.search.algorithm.Algorithm;
import org.harbu.search.algorithm.BruteForceSearch;
import org.harbu.search.problem.slidingpuzzle.NPuzzleState;
import org.harbu.search.problem.constraint.ConstraintSatisfaction;
import org.harbu.search.problem.constraint.impl.NQueensState;
import org.harbu.search.problem.constraint.impl.SudokuState;
import org.harbu.search.problem.slidingpuzzle.NPuzzle;
import org.harbu.search.problem.tsp.TSP;
import org.harbu.search.problem.tsp.TSPState;
import org.harbu.search.util.CompleteGraph;
import org.harbu.search.util.ProblemRandomizer;
import java.util.Arrays;

/**
 *
 * @author harbu
 */
public class Main {
    
    public static void main(String[] args) {
        sudoku();
    }

    public static void slidingPuzzle() {
        NPuzzleState goal = NPuzzleState.createFifteenPuzzle(new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        });

        NPuzzleState start = ProblemRandomizer.randomizeProblem(goal, 500);
        Algorithm<NPuzzleState> a = new AStar<>(new NPuzzle(start, goal));
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
                "Tallinna", "Espoo", "Inkoo", "Nuokio", "Malmi", "Nääksiä", "Rulola", "Malaria"),
                new double[][]{
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
                    {145, 623, 90, 124, 367, 629, 125, 731, 259, 672, 156},
                    {942, 532, 125, 631, 356, 124, 63, 215, 631, 564, 619, 124},
                    {145, 623, 500, 425, 623, 532, 319, 59, 523, 699, 958, 5122, 259},
                    {146, 900, 953, 800, 756, 312, 20, 532, 629, 623, 195, 623, 256, 513},
                    {646, 620, 353, 540, 90, 732, 620, 132, 929, 323, 115, 603, 286, 472, 536},
                    {646, 620, 353, 540, 90, 732, 620, 132, 929, 323, 115, 603, 286, 472, 536, 2162},
                    {125, 20, 153, 340, 802, 730, 628, 132, 122, 323, 165, 603, 783, 72, 136, 2162, 321},
                    {125, 20, 153, 340, 802, 730, 628, 132, 122, 323, 165, 603, 783, 72, 136, 2162, 321, 51}
                    
                    
                }
        );
        
        TSPState start = TSPState.makeTSPStart(graph, "Vantaa");
        Algorithm<TSPState> a = new AStar<>(new TSP(start));
        a.solve();
        System.out.println(start);
        System.out.println("-----");
        System.out.println(a.getPathToGoal());
        System.out.println("Number of steps required: " + a.getPathToGoal().size());
    }
    
    public static void queens() {
        NQueensState problem = new NQueensState(100);
        Algorithm algorithm = new BruteForceSearch(
                new ConstraintSatisfaction(problem),
                BruteForceSearch.SearchType.DEPTH_FIRST);
        algorithm.solve();
        System.out.println(algorithm.getPathToGoal().get(algorithm.getPathToGoal().size()-1));
    }
    
     public static void sudoku() {
        SudokuState problem = new SudokuState(new int[][]{
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        });
        Algorithm algorithm = new BruteForceSearch<>(
                new ConstraintSatisfaction(problem),
                BruteForceSearch.SearchType.DEPTH_FIRST);
        algorithm.solve();
        System.out.println(algorithm.getPathToGoal());
    }
}
