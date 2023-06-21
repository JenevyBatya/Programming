package org.lab_5.Commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.lab_5.CommandExecute;
import org.lab_5.Models.*;
import org.lab_5.OrganizationRegistration;
import org.lab_5.Request;
import org.lab_5.Updater;

import java.util.Hashtable;

public class Update implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;
    ;

    public Update(Hashtable organizationTable) {
        this.organizationTable = organizationTable;
    }

    private static String name = "update";

    private String description = name + " id:  обновление значения элемента коллекции, id которого равен заданному\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    CommandExecute commandExecute;

    public CommandExecute execute(Request o) {
        String name; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates; //Поле не может быть null
        int xCoordinates;
        Long yCoordinates;
        java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Double annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
        String fullName; //Поле может быть null
        Integer employeesCount; //Поле может быть null, Значение поля должно быть больше 0
        String type; //Поле может быть null
        String street;
        Long xLocation;
        Float yLocation;
        float zLocation;
        try {
            //TODO
            int id = Integer.parseInt(o.getArg());
            String[] data = o.getData().split(" ");
            if (organizationTable.containsKey(id)) {
                Updater updater = new Updater();

                name = (String) updater.compare(data[0], organizationTable.get(id).getName());
                xCoordinates = (int) updater.compare(data[1], organizationTable.get(id).getCoordinates().getX());
                yCoordinates = (Long) updater.compare(data[2], organizationTable.get(id).getCoordinates().getY());
                annualTurnover = (Double) updater.compare(data[3], organizationTable.get(id).getAnnualTurnover());
                creationDate = organizationTable.get(id).getCreationDate();
                fullName = (String) updater.compare(data[4], organizationTable.get(id).getFullName());
                employeesCount = (Integer) updater.compare(data[5], organizationTable.get(id).getEmployeesCount());
                type = (String) updater.compare(data[6], organizationTable.get(id).getType().toString());
                street = (String) updater.compare(data[7], organizationTable.get(id).getPostalAddress().getStreet());
                xLocation = (Long) updater.compare(data[8], organizationTable.get(id).getPostalAddress().getTown().getX());
                yLocation = (Float) updater.compare(data[9], organizationTable.get(id).getPostalAddress().getTown().getY());
                zLocation = (float) updater.compare(data[10], organizationTable.get(id).getPostalAddress().getTown().getZ());

                organizationTable.put(id, new Organization(id, name, new Coordinates(xCoordinates, yCoordinates),
                        creationDate, annualTurnover, fullName, employeesCount, OrganizationType.valueOf(type),
                        new Address(street, new Location(xLocation, yLocation, zLocation))));
//                if (!data[0].equals(null)) {
                return new CommandExecute("Данные организации были успешно обновлены", true);
            }else{
                return new CommandExecute("Организации с id_" + id + " не существует", false);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
