package idastar.problems;

public interface Heuristic<T extends Problem<T>> {

    public int calculate(T node);
}
