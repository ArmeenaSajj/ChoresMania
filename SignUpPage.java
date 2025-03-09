package choresmania;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpPage extends JFrame {
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField securityQuestionField;
    private JTextField securityAnswerField;

    private login loginPage;

    public SignUpPage() {
        initializeUI();
    }

    public void setLoginPage(login loginPage) {
        this.loginPage = loginPage;
    }

    private void initializeUI() {
        // Set the title and size of the window
        setTitle("Sign Up");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with null layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Set background color
        panel.setBackground(new Color(247, 205, 208));

        // Create header
        JLabel headersignup = new JLabel("Signup");
        headersignup.setBounds(420, 50, 500, 80);
        headersignup.setFont(new Font("Serif", Font.BOLD, 55));

        // Labels and text fields for user input
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(350, 200, 200, 30);
        nameLabel.setFont(new Font("", Font.BOLD, 20));
        nameField = new JTextField();
        nameField.setBounds(500, 200, 200, 40);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(350, 280, 200, 30);
        usernameLabel.setFont(new Font("", Font.BOLD, 20));
        usernameField = new JTextField();
        usernameField.setBounds(500, 280, 200, 40);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(350, 360, 200, 30);
        passwordLabel.setFont(new Font("", Font.BOLD, 20));
        passwordField = new JPasswordField();
        passwordField.setBounds(500, 360, 200, 40);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(350, 440, 200, 30);
        emailLabel.setFont(new Font("", Font.BOLD, 20));
        emailField = new JTextField();
        emailField.setBounds(500, 440, 200, 40);

        JLabel securityQuestionLabel = new JLabel("Security Q:");
        securityQuestionLabel.setFont(new Font("", Font.BOLD, 20));
        securityQuestionLabel.setBounds(350, 520, 200, 30);
        securityQuestionField = new JTextField();
        securityQuestionField.setBounds(500, 520, 200, 40);

        JLabel securityAnswerLabel = new JLabel("Security A:");
        securityAnswerLabel.setFont(new Font("", Font.BOLD, 20));
        securityAnswerLabel.setBounds(350, 600, 200, 30);
        securityAnswerField = new JTextField();
        securityAnswerField.setBounds(500, 600, 200, 40);

        // Sign Up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(450, 710, 100, 40);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

        // Adding components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(headersignup);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(securityQuestionLabel);
        panel.add(securityQuestionField);
        panel.add(securityAnswerLabel);
        panel.add(securityAnswerField);
        panel.add(signUpButton);

        // Add the panel to the window
        add(panel);
        setVisible(true);
    }

    // Method to handle sign up process
    private void signUp() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        String securityQuestion = securityQuestionField.getText();
        String securityAnswer = securityAnswerField.getText();

        // Check if any field is empty
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()
                || securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sign up not successful. Please try again.");
        } else {
            saveUserInformation(name, username, password, email, securityQuestion, securityAnswer);
            JOptionPane.showMessageDialog(this, "Sign up successful!");
            dispose();

            // Clear the fields after sign-up
            nameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            securityQuestionField.setText("");
            securityAnswerField.setText("");

            // Create an instance of the login page and pass the file name
            login login = new login("user_information.txt");
            login.setVisible(true);
        }
    }

    // Method to save user information to a file
    private void saveUserInformation(String name, String username, String password, String email,
                                     String securityQuestion, String securityAnswer) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("user_information.txt", true));
            writer.println("Name: " + name);
            writer.println("Username: " + username);
            writer.println("Password: " + password);
            writer.println(username + " Login: false");
            writer.println("Email: " + email);
            writer.println("Security Question: " + securityQuestion);
            writer.println("Security Answer: " + securityAnswer);
            writer.println(username + " Chores: a, b, c, d");
            writer.println(username + " Grocery list: ");
            writer.println(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
