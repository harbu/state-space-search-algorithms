package idastar.util;

import idastar.problems.Operation;
import java.util.List;
import java.util.Random;
import idastar.problems.State;

public class ProblemRandomizer {

    private static final int ROUNDS = 9000000;
    private static final Random randomGenerator = new Random();

    public static <T extends State<T>> T randomizeProblem(T state) {
        for (int i = 0; i < ROUNDS; ++i) {
            state = pickRandom(state.getOperations());
        }
        return state;
    }

    private static <T extends State<T>> T pickRandom(List<Operation<T>> moves) {
        int index = randomGenerator.nextInt(moves.size());
        return moves.get(index).getNode();
    }
}
