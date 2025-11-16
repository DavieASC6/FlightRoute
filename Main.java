package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFile = "src/main/resources/flights.csv";  // adjust if needed
        Graph graph = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                if (header) {  // skip header
                    header = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length < 3) continue;

                String source = values[0].trim();
                String destination = values[1].trim();
                int distance = Integer.parseInt(values[2].trim());

                graph.addEdge(source, destination, distance);
                graph.addEdge(destination, source, distance); // for undirected routes
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("✅ Flight graph loaded successfully!\n");
        graph.printGraph();


        // After graph.printGraph();
        Dijkstra dijkstra = new Dijkstra(graph);

        // Example: find the shortest path
        dijkstra.findShortestPath("JFK", "SEA");

        dijkstra.findShortestPath("JFK", "ATL");

        dijkstra.findShortestPath("EWR", "PHX");

        // Example: Find the longest route (most expensive or time-consuming)
        LongestPath longestPath = new LongestPath(graph);
        longestPath.findLongestPath("JFK", "SEA");


    }
}
