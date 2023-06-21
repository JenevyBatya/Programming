package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.sql.Connection;
import java.util.Hashtable;

public class Show implements BaseCommand{


    private Hashtable<Integer, Organization> organizationTable;
    private Connection connection;
    public Show(Hashtable organizationTable, Connection connection){
        this.organizationTable = organizationTable;
        this.connection=connection;
    }
    private static String name = "show";

    private String description = name + ": выведение в стандартный поток вывода всех элементов коллекции в строковом представлении\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) {
       String response = "";
        if (organizationTable.size() != 0) {
            for (Organization organization : organizationTable.values()) {
                response+="id_" + organization.getId() + " " + organization.getName() + ", Annual turnover: " + organization.getAnnualTurnover() + ", Organization type: " + organization.getType().toString()+"\n";
            }
        }else{
            return new CommandExecute("Коллекция в данный момент не содержит в себе объектов",false);
        }
        return new CommandExecute(response,true);
    }
    public void setUserId(Integer userId) {

    }

}
