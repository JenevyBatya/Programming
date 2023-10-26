package org.lab.Gui;

import org.lab.CommandExecute;
import org.lab.UserAuthentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    BufferedReader input;
    PrintWriter output;
    Socket socket;
    private JTextField usernameField;
    private JPasswordField passwordField;
    CommandExecute commandExecute;

    public LoginPage(BufferedReader input, PrintWriter output, Socket socket) {
        this.input=input;
        this.output=output;
        this.socket=socket;
        setTitle("Авторизация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel usernameLabel = new JLabel("Имя пользователя:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Зарегистрироваться");
        JButton loginButton = new JButton("Войти");

        int buttonWidth = 120;
        int buttonHeight = 30;
        registerButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        passwordField.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        usernameField.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        leftPanel.add(usernameLabel);
        leftPanel.add(passwordLabel);
        leftPanel.add(registerButton);

        rightPanel.add(usernameField);
        rightPanel.add(passwordField);
        rightPanel.add(loginButton);

        panel.add(leftPanel);
        panel.add(rightPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAuthentication userAuthentication = new UserAuthentication();
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Невалидные данные в полях", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        commandExecute = userAuthentication.authenticateUser("/reg", username, password);
                        System.out.println(commandExecute);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (commandExecute.isSuccess()) {

                        ThreeSectionFrame threeSectionFrame = new ThreeSectionFrame(commandExecute.getHashtable(), input, output, socket);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, commandExecute.getResponse(), "Предупреждение", JOptionPane.WARNING_MESSAGE);

                    }
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAuthentication userAuthentication = new UserAuthentication();
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Невалидные данные в полях", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        commandExecute = userAuthentication.authenticateUser("/login", username, password);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (commandExecute.isSuccess()) {

                        ThreeSectionFrame threeSectionFrame = new ThreeSectionFrame(commandExecute.getHashtable(), input, output, socket);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, commandExecute.getResponse(), "Предупреждение", JOptionPane.WARNING_MESSAGE);

                    }
                }
            }
        });

        add(panel);
        setVisible(true);
    }



//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new LoginPage();
//            }
//        });
//    }
}
