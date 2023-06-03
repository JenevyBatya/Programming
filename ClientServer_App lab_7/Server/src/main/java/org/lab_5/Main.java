package org.lab_5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String SSH_HOST = "se.ifmo.ru";
    private static final String SSH_USER = "s367614";
    private static final String SSH_PASSWORD = "qHQg)1617";
    private static final int SSH_PORT = 2222;
    private static final String BD_USERNAME = "s367614";
    private static final String BD_PASSWORD = "B9Kj817gSGRi2tb6";
    private static final String BD_URL = "jdbc:postgresql://localhost:5432/studs";
    private static final int THREAD_POOL_SIZE = 5; // Количество потоков в пуле

    public static void main(String[] args) throws IOException, SQLException, JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SSH_USER, SSH_HOST, SSH_PORT);
        session.setPassword(SSH_PASSWORD);

        // Настройка параметров сессии SSH
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        // Установка соединения через SSH
        session.connect();

        // Установка проброски порта
        int localPort = 1236;
        session.setPortForwardingL(localPort, "localhost", 5432);

        // Подключение к базе данных через SSH туннель
        String url = "jdbc:postgresql://localhost:" + localPort + "/" + "studs";
//        Connection connection = DriverManager.getConnection(url, BD_USERNAME, BD_PASSWORD);

//        Statement statement = connection.createStatement();

        ConsoleLog consoleLog = new ConsoleLog();
        CommandsMode commandsMode = new CommandsMode();

        boolean mode = true;
        // Создаем серверный сокет и слушаем указанный порт
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running and listening on port 1234");

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // Бесконечный цикл для обработки клиентских подключений
        while (true) {
            boolean success = false;
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(() -> {
//                if (mode) {
                try {
                    // Принимаем клиентский сокет
//                        Socket finalClientSocket1 = clientSocket;
                    Integer userId = null;
                    RequestAnswer requestAnswer = new RequestAnswer();
                    consoleLog.consoleResp("Accepted connection from client " + clientSocket.getInetAddress());
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                    Connection connection = DriverManager.getConnection(url, BD_USERNAME, BD_PASSWORD);
                    Statement statement = connection.createStatement();

                    //Registration or logIn of User
                    UserAuthentication userAuthentication = new UserAuthentication(connection, input, output);


                    FileImportMode fileImportMode = new FileImportMode(statement);
                    while (!success) {
                        CommandExecute systemIn = userAuthentication.getIntoSystem();
                        output.println(requestAnswer.sendPackage(systemIn));
                        consoleLog.consoleRespCommand(systemIn);
                        if (systemIn.isSuccess()) {
                            userId = systemIn.getDetail();
                            break;
                        }
                    }
                    // Выполнение задачи в отдельном потоке из пула
//                        Socket finalClientSocket = finalClientSocket1;
//                        threadPool.execute(() -> {
                    try {
                        commandsMode.executeCommand(fileImportMode.importMode(), input, output, userId, connection);
                        clientSocket.close();
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
//                        });

                } catch (RuntimeException | IOException | SQLException e) {
//                        consoleLog.consoleResp("Соединение с клиентом " + clientSocket.getInetAddress() + " закрыто");
                    System.out.println(e);
                }


//                }
            });
        }

        // Завершение работы пула потоков
//        executorService.shutdown();
    }
}