package quizplatform;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (DatabaseManager.validateUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Proceed to quiz selection or quiz UI
                    String[] quizzes = DatabaseManager.getQuizzes();
                    String quizTitle = (String) JOptionPane.showInputDialog(null, "Select a quiz", "Quiz Selection", JOptionPane.PLAIN_MESSAGE, null, quizzes, quizzes[0]);
                    if (quizTitle != null) {
                        new QuizFrame(quizTitle);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (DatabaseManager.registerUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. Username may already exist.");
                }
            }
        });

        setVisible(true);
    }
}

