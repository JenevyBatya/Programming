package org.lab_5.Commands;

import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Show implements BaseCommand{

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

    public void execute(Object... o) {
        if (organizationTable.size() != 0) {
            for (Organization organization : organizationTable.values()) {
                System.out.println("id_" + organization.getId() + " " + organization.getName() + ", Annual turnover: " + organization.getAnnualTurnover() + ", Organization type: " + organization.getType().toString());
            }
        }else{
            System.out.println("Коллекция в данный момент не содержит в себе объектов");
        }
    }
}
