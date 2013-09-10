package idastar.problems;

public interface Goal<T extends State<T>> {

    public boolean isGoalReached(T currentState);
}
