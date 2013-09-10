package org.harbu.search.algorithm;

import org.harbu.search.problem.Operation;
import org.harbu.search.problem.Problem;
import org.harbu.search.problem.State;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BruteForceSearch<T extends State<T>> extends Algorithm<T> {
    private final SearchType searchType;

    public enum SearchType {
        BREADTH_FIRST,
        DEPTH_FIRST
    }

    public BruteForceSearch(Problem<T> problem, SearchType searchType) {
        super(problem);
        this.searchType = searchType;
    }

    @Override
    public boolean solve() {
        Set<T> closedSet = new HashSet<>();
        Deque<T> openList = new LinkedList<>();
        Map<T,T> pathTo = new HashMap<>();
        
        putToOpenList(openList, start);
        closedSet.add(start);
        pathTo.put(start, null);
        
        while (!openList.isEmpty()) {
            T node = openList.pop();
            
            if (goal.isGoalReached(node)) {
                rebuildPath(pathTo, node);
                return true;
            }
            
            for (Operation<T> move : node.getOperations()) {
                T neighbour = move.getNode();
                if (!closedSet.contains(neighbour)) {
                    pathTo.put(neighbour, node);
                    closedSet.add(neighbour);
                    putToOpenList(openList, neighbour);
                }
            }
        }
        
        return false;
    }
    
    private void putToOpenList(Deque<T> openList, T node) {
        if (searchType == SearchType.BREADTH_FIRST) {
            openList.addLast(node);
        } else {
            openList.addFirst(node);
        }
    }
    
    
    private void rebuildPath(Map<T,T> pathTo, T node) {
        pathToGoal = new LinkedList<>();
        while (node != null) {
            pathToGoal.addFirst(node);
            node = pathTo.get(node);
        }
    }
}
