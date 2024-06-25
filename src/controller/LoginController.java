/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Muh Salahuddin
 */
import view.LoginForm;

public class LoginController {
    private LoginForm loginForm;

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public void login(String username, String password) {
        // Logika autentikasi pengguna
        // Misalnya cek username dan password dari database atau data dummy
        if ("user".equals(username) && "password".equals(password)) {
            System.out.println("Login sukses!");
            // Lanjutkan ke dashboard atau tampilan berikutnya
        } else {
            System.out.println("Login gagal!");
        }
    }
}

