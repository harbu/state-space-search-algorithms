package idastar.util;

public class Edge implements Comparable<Edge> {

    private final String from;
    private final String to;
    private final int cost;

    public Edge(String from, String to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.getCost() < o.getCost()) {
            return -1;
        } else if (this.getCost() > o.getCost()) {
            return 1;
        } else {
            return 0;
        }
    }
}
