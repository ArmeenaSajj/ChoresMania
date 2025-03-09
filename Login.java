package choresmania;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String fileName;

    public Login(String fileName) {
        this.fileName = fileName;
        initializeUI();
    }

    private void initializeUI() {
        // Set the title and size of the window
        setTitle("Login");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with null layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Set background color
        panel.setBackground(new Color(247, 205, 208));

        // Create header
        JLabel header = new JLabel("Login");
        header.setBounds(260, 50, 300, 80);
        header.setFont(new Font("Serif", Font.BOLD, 55));

        // Labels and text fields for user input
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 180, 200, 30);
        usernameLabel.setFont(new Font("", Font.BOLD, 20));
        usernameField = new JTextField();
        usernameField.setBounds(300, 180, 200, 40);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 250, 200, 30);
        passwordLabel.setFont(new Font("", Font.BOLD, 20));
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 250, 200, 40);

        ImageIcon cat_pic = new ImageIcon("/Users/armee/choresmania/demo/src/main/java/choresmania/cat2.png");
        JLabel cat_label = new JLabel(cat_pic);
        cat_label.setBounds(50,5, 1600, 1500);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(260, 320, 80, 40);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });

        // Adding components to the panel
        panel.add(header);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        // Add the panel to the window
        add(panel);
        setVisible(true);
    }

    // Method to handle login process
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Read the user information from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean found = false;
            Scanner sc = new Scanner(new File(fileName));
            //instantiating the StringBuffer class
            StringBuffer buffer = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            System.out.println("Contents of the file: "+fileContents);
            //closing the Scanner object
            sc.close();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String savedUsername = line.substring(10);

                    // Read the next line for password
                    String passwordLine = reader.readLine();
                    if (passwordLine != null && passwordLine.startsWith("Password: ")) {
                        String savedPassword = passwordLine.substring(10);

                        if (username.equals(savedUsername) && password.equals(savedPassword)) {
                            found = true;
                            
                            String loginInfo = reader.readLine();
                            String newLogin = username + " Login: true";
                            fileContents = fileContents.replace(loginInfo, newLogin);
                            FileWriter writer = new FileWriter(fileName);
                            writer.append(fileContents);
                            System.out.println(loginInfo);
                            writer.close();
                            break;
                        }
                    }
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                Dashboard dashboard = new Dashboard();
                // Place your code here for the next step after successful login
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Please try again.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while reading the file.");
        }

        // Clear the fields after login attempt
        usernameField.setText("");
        passwordField.setText("");
    }
}
