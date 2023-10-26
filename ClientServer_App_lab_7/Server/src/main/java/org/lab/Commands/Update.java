package org.lab.Commands;

import org.lab.*;
import org.lab.Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class Update implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    ;

    public Update(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection=connection;
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

    public CommandExecute execute(Request o) throws SQLException {
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
            String sql = "select * from organization where (user_id=? and id=?)";
            PreparedStatement whatUpdate = connection.prepareStatement(sql);
            whatUpdate.setInt(1, userId);
            whatUpdate.setInt(2, id);
            ResultSet resultSet = whatUpdate.executeQuery();
            if (resultSet.next()) {
                Updater updater = new Updater();

                name = (String) updater.compare(data[0], resultSet.getString("name"));
                xCoordinates = (int) updater.compare(data[1], resultSet.getInt("coordinateX"));
                yCoordinates = (Long) updater.compare(data[2], resultSet.getLong("coordinateY"));
                annualTurnover = (Double) updater.compare(data[3], resultSet.getDouble("annualTurnover"));
                creationDate = resultSet.getDate("creationDate").toLocalDate();
                fullName = (String) updater.compare(data[4], resultSet.getString("fullName"));
                employeesCount = (Integer) updater.compare(data[5], resultSet.getInt("employeesCount"));
                type = (String) updater.compare(data[6], resultSet.getString("type"));
                street = (String) updater.compare(data[7], resultSet.getString("street"));
                xLocation = (Long) updater.compare(data[8], resultSet.getLong("locationX"));
                yLocation = (Float) updater.compare(data[9], resultSet.getFloat("locationY"));
                zLocation = (float) updater.compare(data[10], resultSet.getFloat("locationZ"));
                String sql1 = "update organization set name=?, coordinateX=?, coordinateY=?, annualTurnover=?," +
                        " fullName=?, employeesCount=?, type=?, street=?, locationX=?, locationY=?, locationZ=? where id=?";
                PreparedStatement update = connection.prepareStatement(sql1);
                update.setString(1, name);
                update.setInt(2, xCoordinates);
                update.setLong(3, yCoordinates);
                update.setDouble(4, annualTurnover);
                update.setString(5, fullName);
                update.setInt(6, employeesCount);
                update.setString(7, type);
                update.setString(8, street);
                update.setLong(9, xLocation);
                update.setFloat(10, yLocation);
                update.setFloat(11, zLocation);
                update.setInt(12, id);
                update.executeUpdate();

                organizationTable.put(id, new Organization(id, name, new Coordinates(xCoordinates, yCoordinates),
                        creationDate, annualTurnover, fullName, employeesCount, OrganizationType.valueOf(type),
                        new Address(street, new Location(xLocation, yLocation, zLocation))));
//                if (!data[0].equals(null)) {
                organizationTable=new FileImportMode(connection.createStatement()).importMode();
                return new CommandExecute("Данные организации были успешно обновлены", true);
            }else{
                organizationTable=new FileImportMode(connection.createStatement()).importMode();
                return new CommandExecute("Организации с id_" + id + " не существует или вы не являетесь создателем записи", false);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
