/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Schedule;
/**
 *
 * @author Muh Salahuddin
 */
public class ScheduleDAO {

    private Connection connection;

    public ScheduleDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM schedules";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nama = rs.getString("nama");
                String ukm = rs.getString("ukm");
                String namaKegiatan = rs.getString("nama_kegiatan");
                String waktuKegiatan = rs.getString("waktu_kegiatan");

                Schedule schedule = new Schedule(nama, ukm, namaKegiatan, waktuKegiatan);
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    // Metode lain sesuai kebutuhan, seperti insert, update, delete, dll.
}