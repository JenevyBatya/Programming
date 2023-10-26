package org.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


//Путь для сохранения: C:\Users\theal\IdeaProjects\Lab_5.1\src\output.json
//Путь для файла с объектами: C:\Users\theal\IdeaProjects\Lab_5.1\src\data.json
//Путь до скрипта: C:\Users\theal\IdeaProjects\Lab_5.1\src\script.txt

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleLog consoleLog = new ConsoleLog();
        CommandsMode commandsMode = new CommandsMode();
        FileImportMode fileImportMode = new FileImportMode();
        boolean mode = true;
        // Создаем серверный сокет и слушаем указанный порт
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running and listening on port 1234");


        // Бесконечный цикл для обработки клиентских подключений
        while (true) {
            Socket clientSocket = new Socket();
            if (mode) {


                try {

                    // Принимаем клиентский сокет
                    clientSocket = serverSocket.accept();
                    consoleLog.consoleResp("Accepted connection from client " + clientSocket.getInetAddress());
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                    commandsMode.executeCommand(fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json"), input, output);
                    clientSocket.close();
                    System.out.println("Connection with client " + clientSocket.getInetAddress() + " closed");

                } catch (SocketException | RuntimeException e) {
                    consoleLog.consoleResp("Соединение с клиентом " + clientSocket.getInetAddress() + "закрыто");

                }
            }
        }

    }
}

