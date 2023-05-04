package org.lab_5.Commands;

import org.lab_5.Models.Organization;

import java.util.*;

public class PrintAscending implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    public PrintAscending(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "print_ascending";

    private String description = name + ": вывод элементов коллекции в порядке возрастания annualTurnover\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void execute(Object... o) {

        Comparator<Organization> sortByAnnualTurnover = new Comparator<>() {
            public int compare(Organization o1, Organization o2) {
                Double o1AnnualTurnover = o1.getAnnualTurnover();
                Double o2AnnualTurnover = o2.getAnnualTurnover();
                return Double.compare(o1AnnualTurnover, o2AnnualTurnover);
            }
        };
        Set<Map.Entry<Integer, Organization>> set = organizationTable.entrySet();

        ArrayList<Map.Entry<Integer, Organization>> arlist = new ArrayList<>(set);
        Collections.sort(arlist, new Comparator<>() {
            public int compare(Map.Entry<Integer, Organization> o1, Map.Entry<Integer, Organization> o2) {
                return sortByAnnualTurnover.compare(o1.getValue(), o2.getValue());
            }
        });
        Hashtable<Integer, Organization> hashtable = new Hashtable<>();
        for (Map.Entry<Integer, Organization> entry : arlist) {
            hashtable.put(entry.getKey(), entry.getValue());
            System.out.println("id_" + entry.getValue().getId() + " " + entry.getValue().getName() + ", Annual turnover: " + entry.getValue().getAnnualTurnover() + ", Organization type: " + entry.getValue().getType().toString());


        }
    }

}
