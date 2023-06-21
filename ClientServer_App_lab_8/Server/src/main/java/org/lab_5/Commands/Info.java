package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.sql.Connection;
import java.util.Hashtable;

public class Info implements BaseCommand {

    private String response;
    private Hashtable<Integer, Organization> organizationTable;
    ;

    public Info(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
    }

    private static String name = "info";
    private String description = name + ": вывод информации о коллекции\n";


    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) {
        response = organizationTable.getClass().getName() + " ";
        response += organizationTable.size();


        return new CommandExecute(response, true);
    }

    public void setUserId(Integer userId) {

    }

}
