package dijkstrasAlgo;

public class Edge {
    String destination;
    double weight;

    public Edge(String destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return destination + "(" + weight + ")";
    }
}
