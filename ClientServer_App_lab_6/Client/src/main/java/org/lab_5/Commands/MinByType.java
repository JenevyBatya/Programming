package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;
import org.lab_5.Models.OrganizationType;

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
