package org.harbu.search.util;

import org.harbu.search.problem.Operation;
import java.util.List;
import java.util.Random;
import org.harbu.search.problem.State;

public class ProblemRandomizer {

    private static final Random randomGenerator = new Random();

    public static <T extends State<T>> T randomizeProblem(T state, int rounds) {
        for (int i = 0; i < rounds; ++i) {
            state = pickRandom(state.getOperations());
        }
        return state;
    }

    private static <T extends State<T>> T pickRandom(List<Operation<T>> moves) {
        int index = randomGenerator.nextInt(moves.size());
        return moves.get(index).getNode();
    }
}
