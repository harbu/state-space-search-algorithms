package org.harbu.search.problem;

public interface Heuristic<T extends State<T>> {

    public double calculate(T node);
}
