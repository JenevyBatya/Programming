package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;

import java.util.*;

public class PrintAscending implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    public PrintAscending(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "print_ascending";

    private String description = name + ": вывод элементов коллекции в порядке возрастания annualTurnover\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    private String response = "";
    public CommandExecute execute(Object... o) {

        return new CommandExecute(null,true);
    }

}
