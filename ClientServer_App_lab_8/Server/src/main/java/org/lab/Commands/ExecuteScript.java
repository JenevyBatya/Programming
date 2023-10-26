package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.CommandsManager;
import org.lab.ConsoleLog;
import org.lab.Models.Organization;
import org.lab.Request;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ExecuteScript implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    private ArrayList<String> history = new ArrayList<>();
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public ExecuteScript(Hashtable organizationTable, ArrayList<String> history, Connection connection){
        this.history=history;
        this.organizationTable = organizationTable;
        this.connection=connection;
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

    public CommandExecute execute(Request o) {
        CommandsManager cm = new CommandsManager(organizationTable, history,connection);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        //TODO
        try {
            String file = o.getArg();
            List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            String[] arr = lines.toArray(new String[0]);
            String[] command;

            for(String line: arr){
                command=line.split(" ");
                try {
                    if (command.length > 1) {
                        response+=command[0] + " " + command[1]+"\n";
                        response+=commandsMap.get(command[0]).execute(new Request(command[1])).getResponse()+"\n";
                    } else {
                        response+=command[0]+"\n";
                        response+=commandsMap.get(command[0]).execute(null).getResponse()+"\n";
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
        }catch (ArrayIndexOutOfBoundsException | SQLException e) {
            return new CommandExecute("Неправильный синтаксис команды. Укажите полный путь до файла после команды",false);
        }
    }

}
