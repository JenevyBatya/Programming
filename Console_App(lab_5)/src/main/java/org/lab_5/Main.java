package org.lab_5;
import java.util.Scanner;


//Путь для сохранения: C:\Users\theal\IdeaProjects\Lab_5.1\src\output.json
//Путь для файла с объектамиC:\Users\theal\IdeaProjects\Lab_5.1\src\data.json
//Путь до скрипта: C:\Users\theal\IdeaProjects\Lab_5.1\src\script.txt

public class Main {
    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);
        FileImportMode fileImportMode = new FileImportMode();
        System.out.println("Введите полный путь до файла или /cancel для выхода из режима загрузки данных");
        String fileName = sc.nextLine();
        CommandsMode commandsMode = new CommandsMode();
        commandsMode.executeCommand(fileImportMode.importMode(fileName));
    }
}