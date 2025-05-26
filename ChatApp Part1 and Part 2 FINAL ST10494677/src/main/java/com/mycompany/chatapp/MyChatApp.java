package com.mycompany.chatapp;

import javax.swing.JOptionPane;

public class MyChatApp {
    public static void displayMenu() {
        String[] options = {"Part 2 - Send Message", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Select an option", "Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            Part2Features.main(null);
        } else {
            JOptionPane.showMessageDialog(null, "Goodbye!");
            System.exit(0);
        }
    }
}
