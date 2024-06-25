/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Muh Salahuddin
 */
public class MySQLConnection {
    // Informasi koneksi ke MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ukes_booking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method untuk mendapatkan koneksi ke database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
