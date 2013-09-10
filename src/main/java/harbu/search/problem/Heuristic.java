package harbu.search.problem;

public interface Heuristic<T extends State<T>> {

    public int calculate(T node);
}
