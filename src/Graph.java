import java.util.*;

class Graph {
    private final Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add an edge between two locations with a given weight (distance/time)
    public void addEdge(int from, int to, double weight) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight)); // Assuming bidirectional roads
    }

    public Map<Integer, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}

// Represents a road (edge) between locations
class Edge {
    int destination;
    double weight;

    public Edge(int destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }
}
