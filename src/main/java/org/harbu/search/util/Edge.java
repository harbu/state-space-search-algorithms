package org.harbu.search.util;

import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Edge)) {
            return false;
        } else {
            return compareTo((Edge) obj) == 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.from);
        hash = 89 * hash + Objects.hashCode(this.to);
        hash = 89 * hash + this.cost;
        return hash;
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

    @Override
    public String toString() {
        return String.format("%s --- (%d) --> %s", from, cost, to);
    }
}
