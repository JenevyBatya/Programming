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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String file = "C:\\Users\\theal\\Desktop\\data.txt";
    private static final List<String> lines;

    static {
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String[] data = lines.toArray(new String[0]);
    private static final String SSH_HOST = data[0].split(" ")[1];
    private static final String SSH_USER = data[1].split(" ")[1];
    private static final String SSH_PASSWORD = data[2].split(" ")[1];
    private static final int SSH_PORT = 2222;
    private static final String BD_USERNAME = data[3].split(" ")[1];
    private static final String BD_PASSWORD =data[4].split(" ")[1];

    public Main() throws IOException {
    }

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
        Connection connection = DriverManager.getConnection(url, BD_USERNAME, BD_PASSWORD);

        Statement statement = connection.createStatement();

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
                    UserAuthentication userAuthentication = new UserAuthentication(connection, input, output);


                    FileImportMode fileImportMode = new FileImportMode(statement);
                    while (!success) {
                        CommandExecute systemIn = userAuthentication.getIntoSystem();
                        systemIn.setHashtable(fileImportMode.importMode());
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