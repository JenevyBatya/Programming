package org.lab;

//Путь для сохранения: C:\Users\theal\IdeaProjects\Lab_5.1\src\output.json
//Путь для файла с объектами: C:\Users\theal\IdeaProjects\Lab_5.1\src\data.json
//Путь до скрипта: C:\Users\theal\IdeaProjects\Lab_5.1\src\script.txt


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        ConsoleLog consoleLog = new ConsoleLog();
        boolean mode = true;
        while (true) {
            if (mode) {
                try {
                    // Создаем клиентский сокет и подключаемся к серверу
                    Socket socket = new Socket("localhost", 1234);
                    consoleLog.consoleResp("Подключении е к серверу localhost:1234");

                    // Получаем входной и выходной потоки данных для обмена сообщениями с сервером
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                    CommandsMode commandsMode = new CommandsMode(socket);
                    commandsMode.executeCommand(input, output);
                    socket.close();
                    mode = false;

                } catch (IOException e) {
                    consoleLog.consoleResp("Client exception: " + e.getMessage());
                    consoleLog.consoleResp("В данный момент сервер недоступен для взаимодействия. Повторная попытка подключения через 10 секунд");
                    Thread.sleep(10000);
                }
            }
        }
    }
}
