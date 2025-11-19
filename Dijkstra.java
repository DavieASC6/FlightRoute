package dijkstrasAlgo;
import java.util.*;

public class Dijkstra {
    private Graph graph;
    public Dijkstra(Graph graph) {
        this.graph = graph;
        
    }
    public void findShortestPath(String start, String end) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (String airportID : graph.getAdjacencyList().keySet()) {
            dist.put(airportID, Double.POSITIVE_INFINITY);
            
        }
        dist.put(start, 0.0);
        pq.add(Map.entry(start, 0.0));

        while (!pq.isEmpty()) {
            String current = pq.poll().getKey();
            if (current.equals(end)) break;
            for (Edge edge : graph.getAdjacencyList().getOrDefault(current, new ArrayList<>())) {
                double newDist = dist.get(current) + edge.weight;
                if (newDist < dist.get(edge.dest)) {
                    dist.put(edge.dest, newDist);
                    prev.put(edge.dest, current);
                    pq.add(Map.entry(edge.dest, newDist));
                    System.out.println("Recalculating " + edge.dest 
                      + ": new edge weight = " + newDist + " from " + current);
                    
                }
                
            }
            
        }
        if (!dist.containsKey(end) || dist.get(end) == Double.POSITIVE_INFINITY) {
            System.out.println("There is no route connecting " + start + " and " + end);
            return;
            
        }
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.add(at);
            
        }
        Collections.reverse(path);
        System.out.println("Shortest path from " + start + " to " + end + ":");
        System.out.println(String.join(" -> ", path));
        System.out.println("Total Cost: " + dist.get(end));
        
    }
    
}
