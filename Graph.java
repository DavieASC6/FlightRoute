package org.example;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination, int weight) {
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
