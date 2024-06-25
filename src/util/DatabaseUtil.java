package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Muh Salahuddin
 */
public class DatabaseUtil {
    private static final String URL = "jdbc:sqlite:db/ukes_booking.db";
    private static Connection connection = null;

    // Method untuk mendapatkan koneksi ke database
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    // Method untuk menutup koneksi ke database
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
