/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Muh Salahuddin
 */

public class BookingDAO {
    private Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String nim = rs.getString("nim");
                String ukm = rs.getString("ukm");
                String activityName = rs.getString("activity_name");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String status = rs.getString("status");

                Booking booking = new Booking(name, nim, ukm, activityName, startTime, endTime, status);
                bookings.add(booking);
            }

        }

        return bookings;
    }

    public boolean addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings (name, nim, ukm, activity_name, start_time, end_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, booking.getName());
            stmt.setString(2, booking.getNim());
            stmt.setString(3, booking.getUkm());
            stmt.setString(4, booking.getActivityName());
            stmt.setTimestamp(5, new Timestamp(booking.getStartTime().getTime()));
            stmt.setTimestamp(6, new Timestamp(booking.getEndTime().getTime()));
            stmt.setString(7, booking.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public void updateBookingStatus(String username, String namaKegiatan, String status) throws SQLException {
        String query = "UPDATE bookings SET status = ? WHERE name = ? AND activity_name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setString(2, username);
            stmt.setString(3, namaKegiatan);

            stmt.executeUpdate();
        }
    }
}
