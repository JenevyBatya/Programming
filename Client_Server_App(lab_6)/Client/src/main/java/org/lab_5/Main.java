package org.lab_5;
import java.net.ConnectException;
import java.util.Scanner;


//Путь для сохранения: C:\Users\theal\IdeaProjects\Lab_5.1\src\output.json
//Путь для файла с объектами: C:\Users\theal\IdeaProjects\Lab_5.1\src\data.json
//Путь до скрипта: C:\Users\theal\IdeaProjects\Lab_5.1\src\script.txt


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                // Создаем клиентский сокет и подключаемся к серверу
                Socket socket = new Socket("localhost", 1234);
                System.out.println("Connected to server at localhost:1234");

                // Получаем входной и выходной потоки данных для обмена сообщениями с сервером
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                // Отправляем сообщение на сервер и читаем ответ
                output.println("Hello from client!");
                String response = input.readLine();
                System.out.println("Received response from server: " + response);
                output.println("Hello from client!");
                output.println("Hello from client!");

                // Закрываем соединение
//            socket.close();
            } catch (IOException e) {
                System.out.println("Client exception: " + e.getMessage());
                System.out.println("В данный момент сервер недоступен для взаимодействия");
                System.out.println("Вы можете повторить попытку подключения, введя команду /retry, или покинуть сессию");
                String answer = sc.nextLine();
//            e.printStackTrace();
            }
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