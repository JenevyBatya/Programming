package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.CommandsManager;
import org.lab.ConsoleLog;
import org.lab.Models.Organization;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;

public class ExecuteScript implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;
    ;

    public ExecuteScript(Hashtable organizationTable) {

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

        CommandsManager cm = new CommandsManager();
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        commandsMap.remove("execute_script");
        try {
            String file = o[0].toString();
            List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            String[] arr = lines.toArray(new String[0]);
            String[] command;

            for (String line : arr) {
                command = line.split(" ");

                try {
                    if (command.length > 1) {
                        response += commandsMap.get(command[0]).execute(command[1]).getResponse() + "\n";
                    } else {
                        response += commandsMap.get(command[0]).execute().getResponse() + "\n";
                    }


                } catch (NullPointerException e) {
                    consoleLog.consoleResp("Неизвестная команда. Для справки по всем доступным командам пропишите help");
                }

            }
            return new CommandExecute(response, true);
        } catch (IOException e) {
            return new CommandExecute("Файл не найден", false);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new CommandExecute("Неправильный синтаксис команды. Укажите полный путь до файла после команды", false);
        }
    }
}