package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;

import java.util.Hashtable;

public class MinByType implements BaseCommand{
    private String response = "";
    private Hashtable<Integer, Organization> organizationTable;;
    public MinByType(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "min_by_type";

    private String description = name + ": вывод объектов из коллекции, значение поля type которых является минимальным, то есть COMMERCIAL\n";
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
