package idastar;

import idastar.algorithm.IDAStar;
import idastar.problems.slidingpuzzle.NPuzzle;
import idastar.problems.Problem;
import idastar.util.ProblemRandomizer;

/**
 *
 * @author harbu
 */
public class Main {

    public static void main(String[] args) {
        Problem goal = NPuzzle.createFifteenPuzzle(new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        });

        Problem start = ProblemRandomizer.randomizeProblem(goal);
        IDAStar idaStar = new IDAStar(start, goal);
        idaStar.solve();
        System.out.println(start);
        System.out.println("-----");
        System.out.println(idaStar.getPathToGoal());
        System.out.println("Number of steps required: " + idaStar.getPathToGoal().size());
    }
}
