package dijkstrasAlgo;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addEdge(String source, String destination, double weight) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public void printGraph() {
        for (String city : adjacencyList.keySet()) {
            System.out.println(city + " -> " + adjacencyList.get(city));
        }
    }
}
