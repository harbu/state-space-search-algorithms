/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar.problems.tsp;

import idastar.algorithm.NoHeuristic;
import idastar.problems.Heuristic;
import idastar.problems.Move;
import idastar.problems.Problem;
import idastar.util.CompleteGraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class TSP implements Problem<TSP> {
    
    private CompleteGraph graph;
    private String startCity;
    private String currentCity;
    private Set<String> citiesNotYetVisited;
    private boolean returnedHome = false;

    
    public static TSP makeTSPStart(CompleteGraph graph, String startLabel) {
        Set<String> citiesToVisit = graph.getVertices();
        citiesToVisit.remove(startLabel);
        return new TSP(graph, startLabel, citiesToVisit);
    }

    public CompleteGraph getGraph() {
        return graph;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public Set<String> getCitiesNotYetVisited() {
        return citiesNotYetVisited;
    }

    public boolean isReturnedHome() {
        return returnedHome;
    }

    @Override
    public List<Move<TSP>> getMoves() {
        List<Move<TSP>> moves = new ArrayList<>();
        if (!citiesNotYetVisited.isEmpty()) {
            for (String city : citiesNotYetVisited) {
                moves.add(new Move<>(new TSP(this, city), graph.cost(currentCity, city)));
            }
        } else if (!returnedHome) {
            TSP finalState = new TSP(this, startCity);
            finalState.returnedHome = true;
            moves.add(new Move<>(finalState, graph.cost(currentCity, startCity)));
        }
        return moves;     
    }

    @Override
    public String toString() {
        return currentCity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null | !(obj instanceof TSP)) {
            return false;
        } else {
            TSP other = (TSP) obj;
            
            if (this.graph != other.graph || !this.startCity.equals(other.startCity)) {
                return false;
            }
            return this.currentCity.equals(other.currentCity)
                    && this.citiesNotYetVisited.equals(other.citiesNotYetVisited)
                    && this.returnedHome == other.returnedHome;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.graph);
        hash = 89 * hash + Objects.hashCode(this.startCity);
        hash = 89 * hash + Objects.hashCode(this.currentCity);
        hash = 89 * hash + Objects.hashCode(this.citiesNotYetVisited);
        hash = 89 * hash + (this.returnedHome ? 1 : 0);
        return hash;
    }
    
    private TSP(CompleteGraph graph, String startCity, Set<String> citiesNotYetVisited) {
        this.graph = graph;
        this.startCity = startCity;
        this.currentCity = startCity;
        this.citiesNotYetVisited = citiesNotYetVisited;
    }
    
    private TSP(TSP previousState, String newCity) {
        this.graph = previousState.graph;
        this.startCity = previousState.startCity;
        this.currentCity = newCity;
        this.citiesNotYetVisited = new HashSet<>(previousState.citiesNotYetVisited);
        this.citiesNotYetVisited.remove(newCity);
    }

}
