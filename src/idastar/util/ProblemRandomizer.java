package idastar.util;

import idastar.problems.Move;
import java.util.List;
import java.util.Random;
import idastar.problems.Problem;

public class ProblemRandomizer {

    private static final int ROUNDS = 80;
    private static final Random randomGenerator = new Random();

    public static Problem randomizeProblem(Problem state) {
        for (int i = 0; i < ROUNDS; ++i) {
            state = pickRandom(state.getMoves());
        }
        return state;
    }

    private static Problem pickRandom(List<Move> moves) {
        int index = randomGenerator.nextInt(moves.size());
        return moves.get(index).getNode();
    }
}
