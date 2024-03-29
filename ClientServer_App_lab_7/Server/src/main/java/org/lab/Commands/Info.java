package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.FileImportMode;
import org.lab.Models.Organization;
import org.lab.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

public class Info implements BaseCommand{

    private String response;
    private Connection connection;
    private Hashtable<Integer, Organization> organizationTable;;
    public Info(Hashtable organizationTable, Connection connection){
        this.organizationTable = organizationTable;
        this.connection=connection;
    }

    private static String name = "info";
    private String description = name + ": вывод информации о коллекции\n";


    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) throws SQLException {
        organizationTable=new FileImportMode(connection.createStatement()).importMode();
        response="Тип коллекции: " + organizationTable.getClass().getName()+"\n";
        response+="Размер коллекции: " + organizationTable.size();


        return new CommandExecute(response,true);
    }
    public void setUserId(Integer userId) {

    }

}
