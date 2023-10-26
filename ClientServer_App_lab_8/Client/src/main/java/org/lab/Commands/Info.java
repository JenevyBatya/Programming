package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;

import java.util.Hashtable;

public class Info implements BaseCommand{

    private String response;
    private Hashtable<Integer, Organization> organizationTable;;
    public Info(Hashtable organizationTable){
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

    public CommandExecute execute(Object... o) {
        return new CommandExecute(null,true);
    }

}
