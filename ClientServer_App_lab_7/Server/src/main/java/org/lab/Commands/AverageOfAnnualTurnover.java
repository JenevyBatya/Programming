package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.FileImportMode;
import org.lab.Models.Organization;
import org.lab.Request;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

public class AverageOfAnnualTurnover implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;
    private Connection connection;

    public AverageOfAnnualTurnover(Hashtable<Integer, Organization> organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection=connection;
    }

    private static String name = "average_of_annual_turnover";

    private String description = name + ": вывод среднего значения поля annualTurnover для всех элементов коллекции\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public CommandExecute execute(Request o) throws SQLException {
        organizationTable=new FileImportMode(connection.createStatement()).importMode();
        double sumOfAnnualTurnover = 0;
        for (Organization organization : organizationTable.values()) {
            sumOfAnnualTurnover += organization.getAnnualTurnover();
        }
        double averageOfAnnualTurnover = sumOfAnnualTurnover / organizationTable.size();

//        System.out.println(averageOfAnnualTurnover);

        return new CommandExecute(String.valueOf(averageOfAnnualTurnover), true);
    }
    public void setUserId(Integer userId) {

    }

}
