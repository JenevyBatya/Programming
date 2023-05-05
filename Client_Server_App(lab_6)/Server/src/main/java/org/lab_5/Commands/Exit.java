package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Exit implements BaseCommand{

    private Hashtable<Integer, Organization> organizationTable;;
    public Exit(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "exit";

    private String description = name + ": завершение работы программы без сохранения несохраненных данных\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public CommandExecute execute(Object... o) {
        System.exit(0);

        return new CommandExecute(null,true);
    }
}
