package com.mycompany.chatapp;

import javax.swing.*;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;

public class Part2Features {
    static int messageCounter = 0;
    static List<String> sentMessages = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            String recipient = JOptionPane.showInputDialog(null, "Enter recipient number (+ followed by digits):");
            if (!recipient.matches("^\\+\\d{1,9}$")) {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue;
            }

            String message = JOptionPane.showInputDialog(null, "Enter your message (max 250 chars):");
            if (message.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce size.");
                continue;
            }

            String messageId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
            String messageHash = generateHash(message);

            String[] options = { "Send", "Disregard", "Store" };
            int choice = JOptionPane.showOptionDialog(null, "Choose what to do with the message:",
                    "Message Action", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) {
                messageCounter++;
                String summary = String.format("MessageID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
                        messageId, messageHash, recipient, message);
                sentMessages.add(summary);
                JOptionPane.showMessageDialog(null, "Message successfully sent.\n\n" + summary);
            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Message discarded.");
            } else if (choice == 2) {
                saveMessageForLater(messageId, messageHash, recipient, message);
                JOptionPane.showMessageDialog(null, "Message successfully stored.");
            }

            int cont = JOptionPane.showConfirmDialog(null, "Do you want to send another message?");
            if (cont != JOptionPane.YES_OPTION) break;
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + messageCounter);
    }

    public static String generateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash)
                hexString.append(Integer.toHexString(0xff & b));
            return hexString.toString().substring(0, 10).toUpperCase();
        } catch (Exception e) {
            return "HASH_ERROR";
        }
    }

    public static void saveMessageForLater(String id, String hash, String to, String content) {
        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            file.write(String.format("{\"id\":\"%s\", \"hash\":\"%s\", \"to\":\"%s\", \"msg\":\"%s\"},\n",
                    id, hash, to, content.replaceAll("\"", "'")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
        }
    }
}
