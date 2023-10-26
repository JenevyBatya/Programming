package org.lab;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    private static BufferedReader input;
    private static PrintWriter output;
    private static Request inputObject;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ReceptionReader receptionReader = new ReceptionReader();

    public UserAuthentication(BufferedReader input, PrintWriter output) {
        UserAuthentication.input = input;
        UserAuthentication.output = output;
    }

    public UserAuthentication() {
    }

    public static CommandExecute getIntoSystem() throws SQLException, IOException {
        while (true) {
            consoleLog.consoleResp("Для дальнейшей работы в системе вам нужно зарегистрировать новый аккаунт" + "\n" + "с помощью команды /reg или войти в существующий посредством /login");
            String command = sc.nextLine();
            if (command.equals("/reg")) {
                while (true) {
                    try {
                        consoleLog.consoleResp("Придумайте и введите через пробел логин и пароль");
                        String[] details = sc.nextLine().split(" ");
                        return authenticateUser(command, details[0], details[1]);
                    } catch (IndexOutOfBoundsException e) {
                        consoleLog.consoleResp("Неверный формат записи данных для регистрации нового пользователя");
                    }
                }
            } else if (command.equals("/login")) {
                while (true) {
                    try {
                        consoleLog.consoleResp("Введите через пробел логин и пароль");
                        String[] details = sc.nextLine().split(" ");
                        return authenticateUser(command, details[0], details[1]);
                    } catch (IndexOutOfBoundsException e) {
                        consoleLog.consoleResp("Неверный формат записи данных для авторизации пользователя");
                    }
                }
            } else {
                return new CommandExecute("Неверный ввод команды входа в систему", false);
            }
        }
    }


    public static CommandExecute authenticateUser(String command, String username, String password) throws SQLException, IOException {
        inputObject = new Request(true, command, username, password);
        String json = objectMapper.writeValueAsString(inputObject);
        output.println(json);
        System.out.println(json);
        String message = input.readLine();
        System.out.println(message);
        return receptionReader.read(message);
    }

//    public static CommandExecute authenticateUser(String command, String username, String password) throws SQLException {
//        String userExists = "select id, password from userDetails where username = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(userExists);
//        preparedStatement.setString(1, username);
//        ResultSet resultSet = preparedStatement.executeQuery(userExists);
//        if (resultSet.next()) {
//            if (resultSet.getString("password").equals(hashPassword(password))) {
//                return new CommandExecute("Добро пожаловать на сервер, " + username, true, resultSet.getInt("id"));
//            } else {
//                return new CommandExecute("Неверный пароль", false);
//            }
//        } else {
//            return new CommandExecute("Данного пользователя не существует", false);
//        }
//    }

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
