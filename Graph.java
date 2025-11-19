package dijkstrasAlgo;
import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addEdge(String s, String dest, double weight) {
        adjacencyList.putIfAbsent(s, new ArrayList<>());
        adjacencyList.putIfAbsent(dest, new ArrayList<>());
        adjacencyList.get(s).add(new Edge(dest, weight));
        
    }
    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
        
    }
    public void printGraph() {
        for (String airportID : adjacencyList.keySet()) {
            System.out.println(airportID + " -> " + adjacencyList.get(airportID));
            
        }
        
    }
    
}
