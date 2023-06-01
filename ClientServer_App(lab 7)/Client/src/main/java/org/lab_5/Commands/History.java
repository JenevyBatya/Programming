package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class History implements BaseCommand{
    private String response = "";

    private Hashtable<Integer, Organization> organizationTable;
    ConsoleLog consoleLog = new ConsoleLog();

    public History(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "history";

    private String description = name + ": вывод последних 15 команд\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public CommandExecute execute(Object... o) throws IOException {
        return new CommandExecute(null,true);
    }


}
