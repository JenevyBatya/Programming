package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;
import org.lab_5.Models.OrganizationType;
import org.lab_5.Request;

import java.util.Hashtable;

public class MinByType implements BaseCommand{
    private String response = "";
    private Hashtable<Integer, Organization> organizationTable;;
    public MinByType(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "min_by_type";

    private String description = name + ": вывод объектов из коллекции, значение поля type которых является минимальным, то есть COMMERCIAL\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) {
        int counter = 0;
        for(Organization organization: organizationTable.values()){
            if(organization.getType().equals(OrganizationType.COMMERCIAL)){
                response+="id_" + organization.getId() + " " + organization.getName()+"\n";
                counter++;
            }
        }
        if(counter == 0){
            response+="В данный момент в коллекции отсутствуют организации с типом COMMERCIAL";
            return new CommandExecute(response,false);
        }
        return new CommandExecute(response,true);
    }

}
