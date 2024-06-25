/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.User;
/**
 *
 * @author Muh Salahuddin
 */
public class UserController {
    private Connection conn;

    public UserController(Connection conn) {
        this.conn = conn;
    }

    // Method untuk melakukan registrasi pengguna baru
    public boolean register(String username, String password, String email) {
        String role = "user"; // atau dapat disesuaikan dengan logika aplikasi Anda

        // Query untuk menyimpan data pengguna baru
        String query = "INSERT INTO user (username, password, email, role) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);   // Mengatur username
            pstmt.setString(2, password);   // Mengatur password
            pstmt.setString(3, email);      // Mengatur email
            pstmt.setString(4, role);       // Mengatur peran pengguna secara default

            // Eksekusi query untuk menyimpan data
            pstmt.executeUpdate();

            // Registrasi berhasil
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal melakukan registrasi. Silakan coba lagi.");
            return false;
        }
    }

    // Method untuk melakukan validasi login pengguna
    public User login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String role = rs.getString("role");
                return new User(username, password, email, role);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metode lain yang diperlukan untuk mengelola pengguna, seperti mengubah password, dll.
}