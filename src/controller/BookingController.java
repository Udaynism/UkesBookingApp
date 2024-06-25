package controller;

import dao.BookingDAO;
import dao.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.Booking;

/**
 *
 * @author Muh Salahuddin
 */

public class BookingController {
    private Connection connection;
    private BookingDAO bookingDAO;

    public BookingController(Connection connection) {
        this.connection = connection;
        this.bookingDAO = new BookingDAO(connection);
    }

    private void ensureConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = SQLiteConnection.getConnection();
            bookingDAO = new BookingDAO(connection);
        }
    }

    public boolean submitBooking(Booking booking) {
        PreparedStatement statement = null;
        boolean success = false;

        try {
            ensureConnection();

            String query = "INSERT INTO bookings (name, nim, ukm, activity_name, start_time, end_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, booking.getName());
            statement.setString(2, booking.getNim());
            statement.setString(3, booking.getUkm());
            statement.setString(4, booking.getActivityName());
            statement.setTimestamp(5, new Timestamp(booking.getStartTime().getTime()));
            statement.setTimestamp(6, new Timestamp(booking.getEndTime().getTime()));
            statement.setString(7, booking.getStatus());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error: Failed to save booking.");
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error: Failed to close statement.");
                ex.printStackTrace();
            }
        }

        return success;
    }

    public boolean approveBooking(String username, String namaKegiatan) {
        try {
            ensureConnection();
            System.out.println("approveBooking called");
            System.out.println("Connection status: " + (connection != null && !connection.isClosed()));

            String query = "UPDATE bookings SET status = 'Disetujui' WHERE name = ? AND activity_name = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, namaKegiatan);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rejectBooking(String username, String namaKegiatan) {
        try {
            ensureConnection();
            System.out.println("rejectBooking called");
            System.out.println("Connection status: " + (connection != null && !connection.isClosed()));

            String query = "UPDATE bookings SET status = 'Ditolak' WHERE name = ? AND activity_name = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, namaKegiatan);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
