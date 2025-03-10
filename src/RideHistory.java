import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RideHistory {
    public static void logRide(int riderId, int driverId, double distance) {
        String sql = "INSERT INTO ridehistory (rider_id, driver_id, distance, ride_status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, riderId);
            stmt.setInt(2, driverId);
            stmt.setDouble(3, distance);
            stmt.setString(4, "Requested");

            stmt.executeUpdate();
            System.out.println("Ride logged successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRideStatus(int riderId, int driverId, String status) {
        String sql = "UPDATE ridehistory SET ride_status = ? WHERE rider_id = ? AND driver_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, riderId);
            stmt.setInt(3, driverId);

            stmt.executeUpdate();
            System.out.println("Ride status updated to: " + status);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
