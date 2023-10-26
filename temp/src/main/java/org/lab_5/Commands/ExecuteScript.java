package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.CommandsManager;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
    ConsoleLog consoleLog = new ConsoleLog();
    private String response = "";

    public CommandExecute execute(Object... o) {
        CommandsManager cm = new CommandsManager(organizationTable, history);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        commandsMap.remove("execute_script");
        try {
            String file = o[0].toString();
            List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            String[] arr = lines.toArray(new String[0]);
            String[] command;

            for(String line: arr){
                command=line.split(" ");
                try {
                    if (command.length > 1) {
                        response+=command[0] + " " + command[1]+"\n";
                        response+=commandsMap.get(command[0]).execute(command[1]).getResponse()+"\n";
                    } else {
                        response+=command[0]+"\n";
                        response+=commandsMap.get(command[0]).execute().getResponse()+"\n";
                    }
                    if (history.size() < 15) {
                        history.add(command[0]);
                    } else {
                        history.remove(0);
                        history.add(command[0]);
                    }

                } catch (NullPointerException e) {
                    consoleLog.consoleResp("Неизвестная команда. Для справки по всем доступным командам пропишите help");
                }
            }return new CommandExecute(response,true);
        }catch (IOException e){
            return new CommandExecute("Файл не найден",false);
        }catch (ArrayIndexOutOfBoundsException e) {
            return new CommandExecute("Неправильный синтаксис команды. Укажите полный путь до файла после команды",false);
        }
    }

}
