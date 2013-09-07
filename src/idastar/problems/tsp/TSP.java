/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idastar.problems.tsp;

import idastar.problems.Heuristic;
import idastar.problems.Move;
import idastar.problems.Problem;
import idastar.util.CompleteGraph;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TSP implements Problem<TSP> {
    
    private CompleteGraph graph;
    private String startCity;
    private String currentCity;
    private List<String> citiesNotYetVisited;
    private boolean hasReturnedHome = false;

    
    public static TSP makeTSPStart(CompleteGraph graph, String startLabel) {
        List<String> citiesToVisit = graph.getNodeLabels();
        citiesToVisit.remove(startLabel);
        return new TSP(graph, startLabel, citiesToVisit);
    }
    
     public static TSP makeTSPGoal(CompleteGraph graph, String startLabel) {
        TSP goal = new TSP(graph, startLabel, new ArrayList<String>());
        goal.hasReturnedHome = true;
        return goal;
    }

    @Override
    public List<Move<TSP>> getMoves() {
        List<Move<TSP>> moves = new ArrayList<>();
        if (!citiesNotYetVisited.isEmpty()) {
            for (String city : citiesNotYetVisited) {
                moves.add(new Move<>(new TSP(this, city), graph.cost(currentCity, city)));
            }
        } else if (!hasReturnedHome) {
            TSP finalState = new TSP(this, startCity);
            finalState.hasReturnedHome = true;
            moves.add(new Move<>(finalState, graph.cost(currentCity, startCity)));
        }
        return moves;     
    }
    
    @Override
    public Heuristic<TSP> createHeuristicToThisNode() {
        return new Heuristic<TSP>() {

            @Override
            public int calculate(TSP node) {
                return 0;
            }
        };
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
            
            if (this.hasReturnedHome || other.hasReturnedHome) {
                return this.hasReturnedHome == other.hasReturnedHome;
            }
            
            return this.currentCity.equals(other.currentCity)
                    && this.citiesNotYetVisited.equals(other.citiesNotYetVisited);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.graph);
        hash = 89 * hash + Objects.hashCode(this.startCity);
        hash = 89 * hash + Objects.hashCode(this.currentCity);
        hash = 89 * hash + Objects.hashCode(this.citiesNotYetVisited);
        hash = 89 * hash + (this.hasReturnedHome ? 1 : 0);
        return hash;
    }
    
    private TSP(CompleteGraph graph, String startCity, List<String> citiesNotYetVisited) {
        this.graph = graph;
        this.startCity = startCity;
        this.currentCity = startCity;
        this.citiesNotYetVisited = citiesNotYetVisited;
    }
    
    private TSP(TSP previousState, String newCity) {
        this.graph = previousState.graph;
        this.startCity = previousState.startCity;
        this.currentCity = newCity;
        this.citiesNotYetVisited = new ArrayList<>(previousState.citiesNotYetVisited);
        this.citiesNotYetVisited.remove(newCity);
    }

}
