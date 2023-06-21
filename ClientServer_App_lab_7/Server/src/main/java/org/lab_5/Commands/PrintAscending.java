package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.FileImportMode;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class PrintAscending implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;
    private Connection connection;
    public PrintAscending(Hashtable organizationTable, Connection connection){
        this.organizationTable = organizationTable;
        this.connection=connection;
    }
    private static String name = "print_ascending";

    private String description = name + ": вывод элементов коллекции в порядке возрастания annualTurnover\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    private String response = "";
    public CommandExecute execute(Request o) throws SQLException {
        organizationTable=new FileImportMode(connection.createStatement()).importMode();

        Comparator<Organization> sortByAnnualTurnover = (o1, o2) -> {
            Double o1AnnualTurnover = o1.getAnnualTurnover();
            Double o2AnnualTurnover = o2.getAnnualTurnover();
            return Double.compare(o1AnnualTurnover, o2AnnualTurnover);
        };
        Set<Map.Entry<Integer, Organization>> set = organizationTable.entrySet();

        ArrayList<Map.Entry<Integer, Organization>> arlist = new ArrayList<>(set);
        Collections.sort(arlist, (o1, o2) -> sortByAnnualTurnover.compare(o1.getValue(), o2.getValue()));
        Hashtable<Integer, Organization> hashtable = new Hashtable<>();
        for (Map.Entry<Integer, Organization> entry : arlist) {
            hashtable.put(entry.getKey(), entry.getValue());
            response+="id_" + entry.getValue().getId() + " " + entry.getValue().getName() + ", Annual turnover: " + entry.getValue().getAnnualTurnover() + ", Organization type: " + entry.getValue().getType().toString()+"\n";

        }
        return new CommandExecute(response,true);
    }
    public void setUserId(Integer userId) {

    }

}
