
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class MyChatApp extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private BufferedWriter writer;
    private BufferedReader reader;
    private static final String HISTORY_FILE = "chat_history.txt";

    public MyChatApp() {
        setTitle("My Chat App");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        loadChatHistory();
        connectToServer();
        setVisible(true);
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 1234);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread thread = new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        chatArea.append("Server: " + message + "\n");
                        saveMessage("Server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            try {
                writer.write(message);
                writer.newLine();
                writer.flush();
                chatArea.append("You: " + message + "\n");
                inputField.setText("");
                saveMessage("You: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMessage(String message) {
        try (BufferedWriter historyWriter = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            historyWriter.write(message);
            historyWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error saving message history.");
        }
    }

    private void loadChatHistory() {
        File file = new File(HISTORY_FILE);
        if (file.exists()) {
            try (BufferedReader historyReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = historyReader.readLine()) != null) {
                    chatArea.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error loading message history.");
            }
        }
    }

    public static void main(String[] args) {
        new MyChatApp();
    }
}
