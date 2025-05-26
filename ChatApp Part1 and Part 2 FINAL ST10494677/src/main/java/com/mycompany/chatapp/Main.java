package com.mycompany.chatapp;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to ChatApp", "ChatApp",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            String username = JOptionPane.showInputDialog("Username:");
            String password = JOptionPane.showInputDialog("Password:");
            if (Login.loginUser(username, password)) {
                MyChatApp.displayMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Login failed.");
            }
        } else if (choice == 1) {
            Login.registerUser();
        }
    }
}
