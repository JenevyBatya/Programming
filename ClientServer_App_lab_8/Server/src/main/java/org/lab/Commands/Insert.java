package org.lab.Commands;


import org.lab.CommandExecute;
import org.lab.Models.*;

import org.lab.Request;

import java.sql.*;
import java.time.LocalDate;
import java.util.Hashtable;


public class Insert implements BaseCommand {
    //    TODO
    private Hashtable<Integer, Organization> organizationTable;
    ;
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Insert(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection = connection;
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
    public CommandExecute execute(Request o) throws SQLException {
        String[] data = o.getData().split(" ");
        System.out.println(data[0]);
        String sql = "insert into organization(name, coordinateX, coordinateY, creationDate, annualTurnover," +
                "fullName, employeesCount, type, street, locationX, locationY, locationZ,user_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, data[0].replaceAll("~", " "));
        preparedStatement.setInt(2, Integer.parseInt(data[1]));
        preparedStatement.setLong(3, Long.parseLong(data[2]));
        preparedStatement.setDate(4, Date.valueOf(LocalDate.parse(data[3])));
        preparedStatement.setDouble(5, Double.parseDouble(data[4]));
        preparedStatement.setString(6, data[5].replaceAll("~", " "));
        preparedStatement.setInt(7, Integer.parseInt(data[6]));
        preparedStatement.setString(8, data[7]);
        preparedStatement.setString(9, data[8].replaceAll("~", " "));
        preparedStatement.setLong(10, Long.parseLong(data[9]));
        preparedStatement.setFloat(11, Float.parseFloat(data[10]));
        preparedStatement.setFloat(12, Float.parseFloat(data[11]));
        preparedStatement.setInt(13, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            organizationTable.put(resultSet.getInt("id"), new Organization(resultSet.getInt("id"),
                    data[0],
                    new Coordinates(Integer.parseInt(data[1]), Long.parseLong(data[2])),
                    LocalDate.parse(data[3]), Double.parseDouble(data[4]), data[5], Integer.parseInt(data[6]),
                    OrganizationType.valueOf(data[7]), new Address(data[8],
                    new Location(Long.parseLong(data[9]), Float.parseFloat(data[10]), Float.parseFloat(data[11])))));
            return new CommandExecute("Организация была успешно добавлена в коллекцию", true);
        } else {
            // Handle the case when the ResultSet is empty
            return new CommandExecute("Failed to add the organization to the collection", false);
        }

    }

}
