package org.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class UserAuthentication {
    private static Statement statement;
    private static Connection connection;
    static ConsoleLog consoleLog = new ConsoleLog();
    static Scanner sc = new Scanner(System.in);
    static RequestReader requestReader = new RequestReader();
    private static BufferedReader input;
    private static PrintWriter output;

    public UserAuthentication(Connection connection, BufferedReader input, PrintWriter output) {
        this.connection = connection;
        this.input = input;
        this.output = output;
    }

    public static CommandExecute getIntoSystem() throws SQLException, IOException {
        while (true) {
            String message = input.readLine();
            Request request = requestReader.read(message);
            String command = request.getCommand();
            String username = request.getUsername();
            String password = request.getPassword();
            consoleLog.consoleResp(command + " " + username + " " + password);
            if (command.equals("/reg")) {
                while (true) {
                    try {
                        return registerUser(username, password);
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            } else if (command.equals("/login")) {
                while (true) {
                    try {
                        return authenticateUser(username, password);
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            } else {
                return new CommandExecute("Неверный ввод команды входа в систему", false);
            }
        }
    }


    public static CommandExecute registerUser(String username, String password) throws SQLException {
        String userExists = "SELECT id, username FROM userDetails WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(userExists);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new CommandExecute("Пользователь с данным именем уже существует", false);
        } else {
            String hashedPassword = hashPassword(password);
            String newUser = "INSERT INTO userDetails(username, password) VALUES (?, ?)";
            PreparedStatement registerUser = connection.prepareStatement(newUser);
            registerUser.setString(1, username);
            registerUser.setString(2, hashedPassword);
            registerUser.executeUpdate();

            // Выполняем отдельный запрос, чтобы получить id зарегистрированного пользователя
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new CommandExecute("Пользователь успешно зарегистрирован в систему", true, resultSet.getInt("id"), null);
            } else {
                return new CommandExecute("Не удалось получить информацию о зарегистрированном пользователе", false);
            }
        }
    }


    public static CommandExecute authenticateUser(String username, String password) throws SQLException {
        String userExists = "select id, password from userDetails where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(userExists);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("password").equals(hashPassword(password))) {
                return new CommandExecute("Добро пожаловать на сервер, " + username, true, resultSet.getInt("id"),null);
            } else {
                return new CommandExecute("Неверный пароль", false);
            }
        } else {
            return new CommandExecute("Данного пользователя не существует", false);
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
