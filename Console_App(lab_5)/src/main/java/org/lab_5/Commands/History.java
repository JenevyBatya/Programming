package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;

import java.util.ArrayList;
import java.util.Hashtable;

public class History implements BaseCommand{
    private String response = "";

    private Hashtable<Integer, Organization> organizationTable;
    ConsoleLog consoleLog = new ConsoleLog();
    ArrayList<String> history = new ArrayList<>();
    public History(Hashtable organizationTable, ArrayList<String> history){
        this.organizationTable = organizationTable;
        this.history=history;
    }
    private static String name = "history";

    private String description = name + ": вывод последних 15 команд\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Object... o) {
        for(String command: history){
            response+=command+"\n";
        }
        return new CommandExecute(response,true);
    }

}
