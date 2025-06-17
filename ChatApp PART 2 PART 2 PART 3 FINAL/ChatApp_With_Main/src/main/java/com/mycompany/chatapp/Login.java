package com.mycompany.chatapp;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Login {
    private HashMap<String, String> userCredentials = new HashMap<>();
    private final String userFile = "user_data.txt";

    public Login() {
        loadUsers();
    }

    public void start() {
        String[] options = {"Login", "Register", "Continue to Chat"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Login/Register",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            loginUser();
        } else if (choice == 1) {
            registerUser();
        } else if (choice == 2) {
            new MyChatApp();
        }
    }

    private void loginUser() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        if (username != null && password != null && userCredentials.containsKey(username) &&
            userCredentials.get(username).equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful.");
            new MyChatApp();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed.");
        }
    }

    private void registerUser() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            userCredentials.put(username, password);
            saveUsers();
            JOptionPane.showMessageDialog(null, "Registration successful.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (String user : userCredentials.keySet()) {
                writer.write(user + "," + userCredentials.get(user));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save user data.");
        }
    }

    private void loadUsers() {
        File file = new File(userFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    userCredentials.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load user data.");
        }
    }
}
