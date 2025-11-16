package org.example;

import java.util.*;

public class LongestPath {

    private Graph graph;
    private List<String> bestPath;
    private int maxCost;

    public LongestPath(Graph graph) {
        this.graph = graph;
        this.bestPath = new ArrayList<>();
        this.maxCost = Integer.MIN_VALUE;
    }

    /**
     * Finds all possible paths from start to end and tracks the longest one by total cost.
     */
    public void findLongestPath(String start, String end) {
        Set<String> visited = new HashSet<>();
        List<String> currentPath = new ArrayList<>();
        currentPath.add(start);

        dfs(start, end, visited, currentPath, 0);

        if (bestPath.isEmpty()) {
            System.out.println("No path found from " + start + " to " + end);
        } else {
            System.out.println("\n🛬 Longest Path from " + start + " to " + end + ":");
            System.out.println(String.join(" -> ", bestPath));
            System.out.println("💰 Total Cost: " + maxCost);
        }
    }

    /**
     * Recursive DFS to explore all paths.
     */
    private void dfs(String current, String end, Set<String> visited, List<String> path, int currentCost) {
        // Mark current node visited
        visited.add(current);

        // Base case: reached destination
        if (current.equals(end)) {
            if (currentCost > maxCost) {
                maxCost = currentCost;
                bestPath = new ArrayList<>(path);
            }
            visited.remove(current);
            return;
        }

        // Explore neighbors
        for (Edge edge : graph.getAdjacencyList().getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(edge.destination)) {
                path.add(edge.destination);
                dfs(edge.destination, end, visited, path, currentCost + edge.weight);
                path.remove(path.size() - 1); // backtrack
            }
        }

        visited.remove(current); // backtrack for other routes
    }
}
