import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Sample Rider
        Rider rider = new Rider(1, "Rider1", 21.6129, 77.2295);

        // Load available drivers
        List<Driver> availableDrivers = DataBaseManager.getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            System.out.println("No available drivers nearby.");
            return;
        }

        // Load Road Network (Graph)
        Graph cityGraph = loadRoadNetwork();

        // Find the nearest driver using the shortest path algorithm
        Driver assignedDriver = findNearestDriver(rider, availableDrivers, cityGraph);
        System.out.println("Assigned Driver: " + assignedDriver.getName());

        // Calculate shortest driving distance
        double shortestDistance = DijkstraAlgorithm.findShortestPath(cityGraph, rider.getId(), assignedDriver.getId());

        // Log ride in database
        RideHistory.logRide(rider.getId(), assignedDriver.getId(), shortestDistance);
        RideHistory.updateRideStatus(rider.getId(), assignedDriver.getId(), "Accepted");

        // Simulate Ride
        simulateRide(rider.getId(), assignedDriver.getId());
    }

    // Method to find nearest driver using Dijkstra’s Algorithm
    private static Driver findNearestDriver(Rider rider, List<Driver> drivers, Graph graph) {
        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            double distance = DijkstraAlgorithm.findShortestPath(graph, rider.getId(), driver.getId());
            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }

    // Load road network as a graph
    private static Graph loadRoadNetwork() {
        Graph graph = new Graph();

        // Add roads between taxi stands, riders, and drivers
        graph.addEdge(1, 2, 5.0); // Example: Rider to Taxi Stand (5 km)
        graph.addEdge(2, 3, 2.0); // Example: Taxi Stand to Driver (2 km)
        graph.addEdge(3, 4, 4.0); // Example: Road between locations (4 km)

        return graph;
    }

    private static void simulateRide(int riderId, int driverId) {
        try {
            Thread.sleep(3000);
            RideHistory.updateRideStatus(riderId, driverId, "Ongoing");

            Thread.sleep(5000);
            RideHistory.updateRideStatus(riderId, driverId, "Completed");

            System.out.println("Ride completed successfully!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
