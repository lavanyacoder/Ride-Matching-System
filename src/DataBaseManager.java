import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/RideMatchingDB";
    private static final String USER = "root";
    private static final String PASSWORD = "LAVANYA";

    public static void main(String[] args) {
        double userLat = 28.6100; // Example user latitude
        double userLon = 77.2300; // Example user longitude
        findNearestTaxiStand(userLat, userLon);
    }

    public static void findNearestTaxiStand(double userLat, double userLon) {
        String query = "SELECT name, latitude, longitude FROM locations";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            String nearestStand = "";
            double minDistance = Double.MAX_VALUE;

            while (rs.next()) {
                String name = rs.getString("name");
                double lat = rs.getDouble("latitude");
                double lon = rs.getDouble("longitude");

                double distance = haversine(userLat, userLon, lat, lon);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestStand = name;
                }
            }

            System.out.println("Nearest Taxi Stand: " + nearestStand + " (" + minDistance + " km away)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Driver> getAvailableDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT id, name, latitude, longitude FROM drivers WHERE status = 'available'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");

                drivers.add(new Driver(id, name, latitude, longitude));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of Earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
