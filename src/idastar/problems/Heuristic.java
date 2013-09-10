package idastar.problems;

public interface Heuristic<T extends State<T>> {

    public int calculate(T node);
}
