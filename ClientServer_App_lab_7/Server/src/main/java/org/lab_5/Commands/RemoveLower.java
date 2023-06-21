package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.FileImportMode;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RemoveLower implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;
    ;
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public RemoveLower(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection = connection;
    }

    private static String name = "remove_lower";

    private String description = name + " id: удаление из коллекции всех элементов, меньших, чем заданные\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private String response;


    public CommandExecute execute(Request o) throws SQLException {

        int id = Integer.parseInt(o.getArg());

        String sql1 = "select id from organization where (id<=? and user_id=?)";
        PreparedStatement idToDelete = connection.prepareStatement(sql1);
        idToDelete.setInt(1, id);
        idToDelete.setInt(2, userId);
        ResultSet resultSet = idToDelete.executeQuery();

        String sql = "delete from organization where (id<=? and user_id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, userId);
        int counter = preparedStatement.executeUpdate();
        System.out.println(counter);
        while (resultSet.next()) {
            organizationTable.remove(resultSet.getInt("id"));
        }
        if (counter == 1) {
            response = "Была удалена 1 organization из коллекции";
        } else if (counter == 0) {
            response = "Не было удалено ни одной записи ввиду отсутствия id, меньших вашему, или вы не являетесь их создателем";
        } else {
            response = "Было удалено " + counter + " organizations из коллекции";
        }
        organizationTable=new FileImportMode(connection.createStatement()).importMode();
        return new CommandExecute(response, true);
    }

}
