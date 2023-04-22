package org.lab_5.Commands;

import org.lab_5.CommandsManager;
import org.lab_5.Models.Organization;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class ExecuteScript implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    private ArrayList<String> history = new ArrayList<>();
    public ExecuteScript(Hashtable organizationTable, ArrayList<String> history){
        this.history=history;
        this.organizationTable = organizationTable;
    }
    private static String name = "execute_script";

    private String description = name + " path: считывание и исполнение скрипта из указанного файла\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(Object... o) {
        CommandsManager cm = new CommandsManager(organizationTable, history);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        commandsMap.remove("execute_script");
        try {
            String file = o[0].toString();
            File txtFile = new File(file);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(txtFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String[] command;

                while ((command = reader.readLine().split(" ")) != null) {
                    try {
                        if (command.length > 1) {
                            System.out.println();
                            System.out.println(command[0] + " " + command[1]);
                            commandsMap.get(command[0]).execute(command[1]);
                        } else {
                            System.out.println(command[0]);
                            commandsMap.get(command[0]).execute();
                            System.out.println();
                        }
                        if (history.size() < 15) {
                            history.add(command[0]);
                        } else {
                            history.remove(0);
                            history.add(command[0]);
                        }

                    } catch (NullPointerException e) {
                        System.out.println("Неизвестная команда. Для справки по всем доступным командам пропишите help");
                    }
                }

        }catch (IOException e){
            System.out.println("Файл не найден");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Неправильный синтаксис команды. Укажите полный путь до файла после команды");
        }
    }
    @Override
    public void execute() {

    }
}
