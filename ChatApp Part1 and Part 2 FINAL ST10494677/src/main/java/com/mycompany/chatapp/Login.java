package com.mycompany.chatapp;

import javax.swing.JOptionPane;

public class Login {
    public static boolean loginUser(String username, String password) {
        return username.equals("admin") && password.equals("pass123");
    }

    public static void registerUser() {
        String username = JOptionPane.showInputDialog("Enter new username:");
        String password = JOptionPane.showInputDialog("Enter new password:");
        JOptionPane.showMessageDialog(null, "User registered: " + username);
    }
}
