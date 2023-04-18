package org.lab_5.Commands;

import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Clear implements BaseCommand{

    private Hashtable<Integer, Organization> organizationTable;;
    public Clear(Hashtable organizationTable){
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

    public void execute(Object... o) {
        organizationTable.clear();
        System.out.println("Чистка коллекции завершена");
    }
}
