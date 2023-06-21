package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.CommandsManager;
import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Help implements BaseCommand {

    private Hashtable<Integer, Organization> organizationTable;
    ;

    public Help(Hashtable organizationTable) {
        this.organizationTable = organizationTable;
    }

    private static String name = "help";
    private String description = name + ": вывод справки по доступным командам для исполнения\n";


    public static String getName() {
        return name;
    }
    private String response = "";

    @Override
    public String getDescription() {
        return description;
    }


    public CommandExecute execute(Object... o) {


        return new CommandExecute(null,true);
    }
}
