package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;


import java.util.Hashtable;

public class AverageOfAnnualTurnover implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;

    public AverageOfAnnualTurnover(Hashtable<Integer, Organization> organizationTable) {
        this.organizationTable = organizationTable;
    }

    private static String name = "average_of_annual_turnover";

    private String description = name + ": вывод среднего значения поля annualTurnover для всех элементов коллекции\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public CommandExecute execute(Object... o) {

        return new CommandExecute(null, true);
    }

}
