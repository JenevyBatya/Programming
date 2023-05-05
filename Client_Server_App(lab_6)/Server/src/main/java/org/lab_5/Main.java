package org.lab_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


//Путь для сохранения: C:\Users\theal\IdeaProjects\Lab_5.1\src\output.json
//Путь для файла с объектами: C:\Users\theal\IdeaProjects\Lab_5.1\src\data.json
//Путь до скрипта: C:\Users\theal\IdeaProjects\Lab_5.1\src\script.txt


public class Main {
    public static void main(String[] args) {
        try {
            // Создаем серверный сокет и слушаем указанный порт
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is running and listening on port 1234");

            // Принимаем клиентские сокеты и обрабатываем их в бесконечном цикле
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from client " + clientSocket.getInetAddress());

                // Получаем входной и выходной потоки данных для обмена сообщениями с клиентом
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                // Читаем сообщение от клиента и отправляем ответ
                String message = input.readLine();
                System.out.println("Received message from client: " + message);
                output.println("Hello from server!");
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

//public class Main {
//    public static void main(String[] args){
//        ConsoleLog consoleLog = new ConsoleLog();
//
//        Scanner sc = new Scanner(System.in);
//        FileImportMode fileImportMode = new FileImportMode();
//        consoleLog.consoleResp("Введите полный путь до файла или /cancel для выхода из режима загрузки данных");
//        String fileName = sc.nextLine();
//        CommandsMode commandsMode = new CommandsMode();
//        commandsMode.executeCommand(fileImportMode.importMode(fileName));
//    }
//}