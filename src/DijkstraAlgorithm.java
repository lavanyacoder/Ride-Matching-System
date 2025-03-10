import java.util.*;

class DijkstraAlgorithm {
    public static double findShortestPath(Graph graph, int start, int end) {
        Map<Integer, Double> distances = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));

        // Initialize distances
        for (int node : graph.getAdjacencyList().keySet()) {
            distances.put(node, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        priorityQueue.add(new Node(start, 0.0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            if (current.id == end) return distances.get(end);

            for (Edge neighbor : graph.getAdjacencyList().getOrDefault(current.id, new ArrayList<>())) {
                double newDist = distances.get(current.id) + neighbor.weight;

                if (newDist < distances.get(neighbor.destination)) {
                    distances.put(neighbor.destination, newDist);
                    priorityQueue.add(new Node(neighbor.destination, newDist));
                }
            }
        }

        return Double.MAX_VALUE; // No path found
    }
}

// Helper class for priority queue
class Node {
    int id;
    double distance;

    public Node(int id, double distance) {
        this.id = id;
        this.distance = distance;
    }
}
