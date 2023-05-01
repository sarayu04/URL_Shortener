package org.example;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainClass2 {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField longURLField;
    private JButton generateButton;
    private String longURL;
    private String tinyURL;

    public MainClass2() {
        frame = new JFrame("URL Shortener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 150));

        panel = new JPanel();
        label = new JLabel("Enter a long URL: ");
        longURLField = new JTextField(30);
        generateButton = new JButton("    Shorten    ");
        generateButton.addActionListener(new GenerateButtonActionListener());

        panel.add(label);
        panel.add(longURLField);
        panel.add(generateButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainClass2();
    }

    private class GenerateButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            longURL = longURLField.getText();
            try {
                URL url = new URL("http://tinyurl.com/api-create.php?url=" + longURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                tinyURL = in.readLine();
                in.close();

                JOptionPane.showMessageDialog(frame, "ShortURL: " + tinyURL);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
