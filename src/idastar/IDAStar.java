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
        FifteenPuzzle puzzle = FifteenPuzzle.createFromArray(new int[][]{
            {1,2,3,4},
            {5,6,0,8},
            {7,10,9,11},
            {12,13,15,14}
        });
        
        long startTime = System.currentTimeMillis();
        for (int i=0; i < 13727000; ++i) {
            List<FifteenPuzzle> moves = puzzle.getMoves();
            Collections.shuffle(moves);
            puzzle = moves.get(0);
        }
        
        System.out.println(puzzle);
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + "ms");
        
       
    }
}
