package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Show implements BaseCommand{
    private String response = "";

    private Hashtable<Integer, Organization> organizationTable;
    public Show(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "show";

    private String description = name + ": выведение в стандартный поток вывода всех элементов коллекции в строковом представлении\n";
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
