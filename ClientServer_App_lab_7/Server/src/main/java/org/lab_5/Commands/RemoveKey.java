package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.FileImportMode;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class RemoveKey implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;
    ;
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public RemoveKey(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection = connection;
    }

    private static String name = "remove_key";

    private String description = name + " id: удаление элемента из коллекции по его ключу\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) throws SQLException {
        int id = Integer.parseInt(o.getArg());

        String sql1 = "select id from organization where (id=? and user_id=?)";
        PreparedStatement idToDelete = connection.prepareStatement(sql1);
        idToDelete.setInt(1, id);
        idToDelete.setInt(2, userId);
        ResultSet resultSet = idToDelete.executeQuery();

        String sql = "delete from organization where (id=? and user_id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, userId);
        preparedStatement.executeUpdate();


        if(resultSet.next()){
            organizationTable.remove(resultSet.getInt("id"));
            organizationTable=new FileImportMode(connection.createStatement()).importMode();
            return new CommandExecute("Организация с id_" + id + " была успешно удалена из реестра", true);
        }else{
            organizationTable=new FileImportMode(connection.createStatement()).importMode();
            return new CommandExecute("Организации с id_" + id + " не существует или вы не являетесь создателем записи", false);
        }

    }

}
