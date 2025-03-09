import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Themainpage extends JFrame {

    public Themainpage() {
        // Creating main window
        JFrame window = new JFrame("Chores Mania");
        window.setSize(1000, 1000);

        // Set the background color
        Container cp = window.getContentPane();
        cp.setBackground(new Color(247, 205, 208));

        // Add title image
        ImageIcon image = new ImageIcon("chores.png");
        JLabel label_image = new JLabel(image);
        label_image.setBounds(180, 50, 600, 500);
        window.add(label_image);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(380, 550, 250, 40);
        window.add(loginButton);

        // Connect login button to login window
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login menu = new Login("user_information.txt");
            }
        });

        // Create sign up button
        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(380, 650, 250, 40);
        window.add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                SignUpPage menu = new SignUpPage();
            }
        });

        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}