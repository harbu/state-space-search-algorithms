package org.harbu.search.algorithm;

/**
 *
 * @author Eric Andrews
 */
public class SearchStats {

    private String algorithmName;
    private String problemName;
    private long nodesExpanded;
    private long nodesGenerated;

    public SearchStats(String algorithmName, String problemName) {
        this.algorithmName = algorithmName;
        this.problemName = problemName;
        this.nodesExpanded = 0;
        this.nodesGenerated = 0;
    }

    public void nodeExpanded() {
        ++nodesExpanded;
    }

    public void nodeGenerated() {
        ++nodesGenerated;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public String getProblemName() {
        return problemName;
    }

    public long getNodesExpanded() {
        return nodesExpanded;
    }

    public long getNodesGenerated() {
        return nodesGenerated;
    }

    @Override
    public String toString() {
        return "----- " + getAlgorithmName() + " @ " + getProblemName() + " -----\n"
                + "Nodes generated: " + getNodesGenerated() + "\n"
                + "Nodes expanded:  " + getNodesExpanded() + "\n";
    }
}
