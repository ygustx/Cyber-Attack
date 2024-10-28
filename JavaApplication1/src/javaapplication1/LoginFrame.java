package javaapplication1;

import javaapplication1.AdminFrame;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            // Aqui você pode adicionar lógica de autenticação
            new AdminFrame();
            dispose();
        });
    }
}
