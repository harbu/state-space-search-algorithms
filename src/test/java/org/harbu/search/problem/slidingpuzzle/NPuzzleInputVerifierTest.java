package org.harbu.search.problem.slidingpuzzle;

import org.harbu.search.problem.slidingpuzzle.NPuzzleException;
import org.harbu.search.problem.slidingpuzzle.NPuzzleInputVerifier;
import org.junit.*;

public class NPuzzleInputVerifierTest {

    @Test(expected = NPuzzleException.class)
    public void testVerify_fail_wrongLength() {
        int[][] layout = new int[][]{{1, 1}, {1, 1}};
        NPuzzleInputVerifier.verify(layout, 3);
    }

    @Test(expected = NPuzzleException.class)
    public void testVerify_fail_notSquare() {
        int[][] layout = new int[][]{
            {1, 1, 1},
            {1, 1},
            {1, 1, 1}
        };
        NPuzzleInputVerifier.verify(layout, 3);
    }

    @Test(expected = NPuzzleException.class)
    public void testVerify_fail_numberOutOfRange() {
        int[][] layout = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        NPuzzleInputVerifier.verify(layout, 3);
    }

    @Test(expected = NPuzzleException.class)
    public void testVerify_fail_containsDuplicateNumber() {
        int[][] layout = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 8}
        };
        NPuzzleInputVerifier.verify(layout, 3);
    }

    @Test
    public void testVerify_ok() {
        int[][] layout = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        NPuzzleInputVerifier.verify(layout, 3);
    }
}