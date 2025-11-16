package dijkstrasAlgo;

import java.util.*;

public class Dijkstra {

    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public void findShortestPath(String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (String city : graph.getAdjacencyList().keySet()) {
            distances.put(city, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);
        pq.add(Map.entry(start, 0.0));

        while (!pq.isEmpty()) {
            String current = pq.poll().getKey();
            if (current.equals(end)) break;
            for (Edge edge : graph.getAdjacencyList().getOrDefault(current, new ArrayList<>())) {
                double newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, current);
                    pq.add(Map.entry(edge.destination, newDist));
                    System.out.println("Recalculating " + edge.destination 
                        + ": new edge weight = " + newDist + " from " + current);
                }
            }
        }

        if (!distances.containsKey(end) || distances.get(end) == Double.POSITIVE_INFINITY) {
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
