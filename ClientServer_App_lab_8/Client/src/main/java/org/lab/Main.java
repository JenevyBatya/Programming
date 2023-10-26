package org.lab;

//Путь для сохранения: C:\Users\theal\IdeaProjects\Lab_5.1\src\output.json
//Путь для файла с объектами: C:\Users\theal\IdeaProjects\Lab_5.1\src\data.json
//Путь до скрипта: C:\Users\theal\IdeaProjects\Lab_5.1\src\script.txt


import org.lab.Gui.LoginPage;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        setUIFont(new FontUIResource(new Font("Arial", Font.PLAIN, 12)));

        ConsoleLog consoleLog = new ConsoleLog();
        boolean mode = true;
        while (true) {
            if (mode) {
                try {
                    boolean success = false;
                    Integer userId = null;
                    // Создаем клиентский сокет и подключаемся к серверу
                    Socket socket = new Socket("localhost", 1234);
                    consoleLog.consoleResp("Подключении е к серверу localhost:1234");

                    // Получаем входной и выходной потоки данных для обмена сообщениями с сервером
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                    CommandsMode commandsMode = new CommandsMode(socket);
                    UserAuthentication userAuthentication = new UserAuthentication(input,output);
                    LoginPage loginPage = new LoginPage(input, output, socket);

                    mode = false;


                } catch (IOException e) {
                    consoleLog.consoleResp("Client exception: " + e.getMessage());
                    consoleLog.consoleResp("В данный момент сервер недоступен для взаимодействия. Повторная попытка подключения через 10 секунд");
                    Thread.sleep(10000);
                }
            }
        }
    }
    private static void setUIFont(FontUIResource font) {
        UIManager.put("Button.font", font);
        UIManager.put("ToggleButton.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("List.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("CheckBoxMenuItem.font", font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("Panel.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("Viewport.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextPane.font", font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("Tree.font", font);
    }
}
