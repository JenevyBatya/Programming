package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;
import org.lab.Request;

import java.util.Hashtable;

public class Clear implements BaseCommand {

    private Hashtable<Integer, Organization> organizationTable;
    ;

    public Clear(Hashtable organizationTable) {
        this.organizationTable = organizationTable;
    }

    private static String name = "clear";

    private String description = name + ": очищение коллекции\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) {
        organizationTable.clear();
        return new CommandExecute("Чистка коллекции завершена", true);
    }

}
