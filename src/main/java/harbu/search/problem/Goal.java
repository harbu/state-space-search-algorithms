package harbu.search.problem;

public interface Goal<T extends State<T>> {

    public boolean isGoalReached(T currentState);
}
