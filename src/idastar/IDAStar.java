/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author harbu
 */
public class IDAStar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FifteenPuzzle goal = FifteenPuzzle.createFromArray(new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
        });
        
        FifteenPuzzle bestCandidate = FifteenPuzzle.createFromArray(new int[][]{
            {1,2,3,4},
            {5,6,0,8},
            {7,10,9,11},
            {12,13,15,14}
        });
        
        FifteenPuzzleHeuristic heuristic = FifteenPuzzleHeuristic.initializeWithGoal(goal);
        
        int smallestDistance = heuristic.calculate(bestCandidate);
        
        FifteenPuzzle currentPuzzle = bestCandidate;
        
        long startTime = System.currentTimeMillis();
        for (int i=0; i < 17227505; ++i) {
            List<FifteenPuzzle> moves = currentPuzzle.getMoves();
         
            boolean changed = false;
            for (FifteenPuzzle candidate : moves) {
                int score = heuristic.calculate(candidate);
                if (score < smallestDistance) {
                    smallestDistance = score;
                    currentPuzzle = candidate;
                    bestCandidate = candidate;
                    changed = true;
                    System.out.println("Found new best at " + score);
                    System.out.println(candidate);
                }
            }
            
            if (!changed) {
                Collections.shuffle(moves);
                currentPuzzle = moves.get(0);
            }
        }
        
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + "ms");
        
       
    }
}
