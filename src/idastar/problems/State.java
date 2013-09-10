package idastar.problems;

import java.util.List;

public interface State<T extends State<T>> {

    public List<Operation<T>> getOperations();
}
