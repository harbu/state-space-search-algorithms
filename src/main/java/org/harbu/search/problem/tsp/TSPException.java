package org.harbu.search.problem.tsp;

/**
 *
 * @author harbu
 */
public class TSPException extends RuntimeException {
    
    private static final String LINE_ERROR = "Should have 3 entries per line, e.g. 'Turku 13.5 26.2'";
    public static final String LABEL_NOT_IN_GRAPH = "Start label \"%s\" not in graph.";

    private TSPException(String message) {
        super(message);
    }
    
    public static TSPException makeLineError() {
        return new TSPException(LINE_ERROR);
    }
    
    public static TSPException makeInvalidLabelError(String startLabel) {
        return new TSPException(String.format(LABEL_NOT_IN_GRAPH, startLabel));
    }
    
}
