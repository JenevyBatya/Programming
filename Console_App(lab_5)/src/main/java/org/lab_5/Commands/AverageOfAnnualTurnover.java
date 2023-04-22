package org.lab_5.Commands;

import org.lab_5.Models.Organization;


import java.util.Hashtable;

public class AverageOfAnnualTurnover implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;
    public AverageOfAnnualTurnover(Hashtable<Integer, Organization> organizationTable){
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


    public void execute(Object... o) {
        double sumOfAnnualTurnover = 0;
        for (Organization organization: organizationTable.values()){
            sumOfAnnualTurnover += organization.getAnnualTurnover();
        }
        double averageOfAnnualTurnover = sumOfAnnualTurnover/organizationTable.size();
        System.out.println(averageOfAnnualTurnover);;

    }
    @Override
    public void execute() {

    }
}
