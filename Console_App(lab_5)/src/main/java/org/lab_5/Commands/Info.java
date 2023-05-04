package org.lab_5.Commands;

import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Info implements BaseCommand{

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

    public void execute(Object... o) {
        System.out.println("Тип коллекции: " + organizationTable.getClass().getName());
        System.out.println("Размер коллекции: " + organizationTable.size());

    }

}
