package org.lab_5.Commands;


import org.lab_5.CommandExecute;
import org.lab_5.Models.*;
import org.lab_5.OrganizationRegistration;
import org.lab_5.RandomNumber;
import org.lab_5.Request;

import java.time.LocalDate;
import java.util.Hashtable;



public class Insert implements BaseCommand{
//    TODO
    private Hashtable<Integer, Organization> organizationTable;;
    public Insert(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "insert";

    private String description = name + ": добавление нового элемента с заданным ключом\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public CommandExecute execute(Request o){
        String[] data = o.getData().split(" ");
        int id = RandomNumber.createRandomNum(organizationTable);
        organizationTable.put(id,new Organization(id,
                data[0],
                new Coordinates(Integer.parseInt(data[1]),Long.parseLong(data[2])),
                LocalDate.parse(data[3]),Double.parseDouble(data[4]),data[5],Integer.parseInt(data[6]),
                OrganizationType.valueOf(data[7]),new Address(data[8],
                new Location(Long.parseLong(data[9]),Float.parseFloat(data[10]),Float.parseFloat(data[11])))));
            return new CommandExecute("Организация была успешно добавлена в коллекцию",true);

    }


}
