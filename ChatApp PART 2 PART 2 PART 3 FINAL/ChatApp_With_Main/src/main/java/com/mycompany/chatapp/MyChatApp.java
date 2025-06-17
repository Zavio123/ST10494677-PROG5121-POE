package com.mycompany.chatapp;

import javax.swing.*;
import java.util.ArrayList;

public class MyChatApp {
    private ArrayList<String> chatHistory = new ArrayList<>();

    public MyChatApp() {
        showChatWindow();
    }

    private void showChatWindow() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Enter message (or type 'exit'):");
            if (input == null || input.equalsIgnoreCase("exit")) {
                break;
            }
            chatHistory.add("You: " + input);
            showChatHistory();
        }
    }

    private void showChatHistory() {
        StringBuilder history = new StringBuilder();
        for (String msg : chatHistory) {
            history.append(msg).append("\n");
        }
        JOptionPane.showMessageDialog(null, history.toString(), "Chat History", JOptionPane.INFORMATION_MESSAGE);
    }
}
