import java.util.*;

//public class RideMatchingSystem {
//    public static void main(String[] args) {
////        List<Rider> riders = Arrays.asList(
////                new Rider(1, "Alice", 10.1, 20.5),
////                new Rider(2, "Bob", 15.2, 30.6)
////        );
////
////        List<Driver> drivers = Arrays.asList(
////                new Driver(1, "John", 15.0, 30.0),
////                new Driver(2, "Mike", 10.0, 20.0)
////        );
////
////        int[][] distanceMatrix = new int[riders.size()][drivers.size()];
////        for (int i = 0; i < riders.size(); i++) {
////            for (int j = 0; j < drivers.size(); j++) {
////                distanceMatrix[i][j] = RideMatcher.calculateDistance(riders.get(i), drivers.get(j));
////            }
////        }
////
////        Map<Integer, Integer> matches = RideMatcher.matchRidersToDrivers(riders, drivers, distanceMatrix);
////        System.out.println("Matched Riders to Drivers:");
////        for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
////            System.out.println("Rider " + entry.getKey() + " is matched with Driver " + entry.getValue());
////        }
////    }
////}
//    }