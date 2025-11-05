import java.util.*;

public class Dijkstra {

    static class Graph {
        private final Map<String, Map<String, Integer>> vertices = new HashMap<>();

        public void addVertex(String name, Map<String, Integer> edges) {
            vertices.put(name, edges);
        }

        public Map<String, Map<String, Integer>> getVertices() {
            return vertices;
        }
    }

    public static Map<String, Integer> dist;
    public static Map<String, String> prev;

    public static void dijkstra(Graph graph, String source) {
        dist = new HashMap<>();
        prev = new HashMap<>();

        for (String v : graph.getVertices().keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }
        dist.put(source, 0);

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        queue.addAll(graph.getVertices().keySet());

        while (!queue.isEmpty()) {
            String u = queue.poll();

            if (dist.get(u) == Integer.MAX_VALUE) break;

            for (Map.Entry<String, Integer> neighbor : graph.getVertices().get(u).entrySet()) {
                String v = neighbor.getKey();
                int alt = dist.get(u) + neighbor.getValue();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    queue.remove(v);
                    queue.add(v);
                }
            }
        }
    }

    public static List<String> getShortestPath(String target) {
        List<String> path = new ArrayList<>();
        for (String at = target; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph();

        g.addVertex("A", Map.of("B", 4, "C", 2));
        g.addVertex("B", Map.of("C", 5, "D", 10));
        g.addVertex("C", Map.of("E", 3));
        g.addVertex("D", Map.of("F", 11));
        g.addVertex("E", Map.of("D", 4));
        g.addVertex("F", new HashMap<>());

        dijkstra(g, "A");

        System.out.println("Shortest distance to each vertex: " + dist);
        System.out.println("Previous vertex map: " + prev);
        System.out.println("Path from A to F: " + getShortestPath("F"));
    }
}
