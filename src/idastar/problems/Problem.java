package idastar.problems;

import java.util.List;

public interface Problem<T extends Problem<T>> {

    public List<Move<T>> getMoves();
}
