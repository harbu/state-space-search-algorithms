package idastar.problems.tsp;

import idastar.problems.Operation;
import idastar.problems.State;
import idastar.util.CompleteGraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class TSPState implements State<TSPState> {
    
    private CompleteGraph graph;
    private String startCity;
    private String currentCity;
    private Set<String> citiesNotYetVisited;
    private boolean returnedHome = false;

    
    public static TSPState makeTSPStart(CompleteGraph graph, String startLabel) {
        Set<String> citiesToVisit = graph.getVertices();
        citiesToVisit.remove(startLabel);
        return new TSPState(graph, startLabel, citiesToVisit);
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
    public List<Operation<TSPState>> getOperations() {
        List<Operation<TSPState>> moves = new ArrayList<>();
        if (!citiesNotYetVisited.isEmpty()) {
            for (String city : citiesNotYetVisited) {
                moves.add(new Operation<>(new TSPState(this, city), graph.cost(currentCity, city)));
            }
        } else if (!returnedHome) {
            TSPState finalState = new TSPState(this, startCity);
            finalState.returnedHome = true;
            moves.add(new Operation<>(finalState, graph.cost(currentCity, startCity)));
        }
        return moves;     
    }

    @Override
    public String toString() {
        return currentCity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null | !(obj instanceof TSPState)) {
            return false;
        } else {
            TSPState other = (TSPState) obj;
            
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
    
    private TSPState(CompleteGraph graph, String startCity, Set<String> citiesNotYetVisited) {
        this.graph = graph;
        this.startCity = startCity;
        this.currentCity = startCity;
        this.citiesNotYetVisited = citiesNotYetVisited;
    }
    
    private TSPState(TSPState previousState, String newCity) {
        this.graph = previousState.graph;
        this.startCity = previousState.startCity;
        this.currentCity = newCity;
        this.citiesNotYetVisited = new HashSet<>(previousState.citiesNotYetVisited);
        this.citiesNotYetVisited.remove(newCity);
    }

}
