package idastar.problems.slidingpuzzle;

import static idastar.problems.slidingpuzzle.NPuzzle.EMPTY_SPACE;
import idastar.util.TwoDArrayUtils;

public class NPuzzleInputVerifier {

    public static void verify(int[][] layout, int n) {
        verifySizeAndShape(layout, n);
        verifyContent(layout, n);
    }
    
    private static void verifySizeAndShape(int[][] initialLayout, int n) {
        if (initialLayout.length != n || !TwoDArrayUtils.isSquare(initialLayout)) {
            throw NPuzzleException.makeSizeError(n);
        }
    }
    
    private static void verifyContent(int[][] initialLayout, int n) {
        boolean[] usedNumbers = new boolean[n * n];
        
        for (int[] row : initialLayout) {
            for (int number : row) {
                
                if (number != EMPTY_SPACE && (number < 1 || number >= n * n)) {
                    throw NPuzzleException.makeRangeError(n);
                }
                
                if (usedNumbers[number]) {
                    throw NPuzzleException.makeNumberUsedError(number);
                }
                
                usedNumbers[number] = true;
            }
        }
    }
}
