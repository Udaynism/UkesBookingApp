/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.ScheduleDAO;
import dao.SQLiteConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Schedule;

/**
 *
 * @author Muh Salahuddin
 */

public class ScheduleController {
    private Connection connection;
    private ScheduleDAO scheduleDAO;

    public ScheduleController() {
        try {
            // Memuat driver SQLite
            Class.forName("org.sqlite.JDBC");

            // Menginisialisasi koneksi
            this.connection = SQLiteConnection.getConnection();
            this.scheduleDAO = new ScheduleDAO(connection);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite tidak ditemukan.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Koneksi database tidak tersedia.");
            e.printStackTrace();
        }
    }

    public List<Schedule> getAllSchedules() {
        return scheduleDAO.getAllSchedules();
    }

    // Tambahan method lainnya sesuai kebutuhan
}


