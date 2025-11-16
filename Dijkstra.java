package org.example;

import java.util.*;

public class Dijkstra {

    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    /**
     * Computes the shortest path between two cities using Dijkstra's algorithm.
     * Returns the total cost and prints the optimal route.
     */
    public void findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        // Initialize all distances as infinity
        for (String city : graph.getAdjacencyList().keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }

        // Distance to start is zero
        distances.put(start, 0);
        pq.add(Map.entry(start, 0));

        while (!pq.isEmpty()) {
            String current = pq.poll().getKey();

            // Stop early if we reached the destination
            if (current.equals(end)) break;

            for (Edge edge : graph.getAdjacencyList().getOrDefault(current, new ArrayList<>())) {
                int newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, current);
                    pq.add(Map.entry(edge.destination, newDist));
                }
            }
        }

        // Reconstruct the shortest path
        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No route found between " + start + " and " + end);
            return;
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("\n🛫 Shortest path from " + start + " to " + end + ":");
        System.out.println(String.join(" -> ", path));
        System.out.println("💰 Total Cost: " + distances.get(end));
    }
}
