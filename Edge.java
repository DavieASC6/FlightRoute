package dijkstrasAlgo;

public class Edge {
    String dest;
    double weight;

    public Edge(String dest, double weight) {
        this.dest = dest;
        this.weight = weight;
        
    }
    @Override
    public String toString() {
        return dest + "(" + weight + ")";
        
    }
    
}
