package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Clear implements BaseCommand {

    private Hashtable<Integer, Organization> organizationTable;
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    ;

    public Clear(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection = connection;
    }

    private static String name = "clear";

    private String description = name + ": очищение коллекции\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) throws SQLException {
        String sql = "delete from organization where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();

        String leftOrganizations = "SELECT id FROM organization";
        ResultSet organizations = connection.createStatement().executeQuery(leftOrganizations);

        Set<Integer> remainingIds = new HashSet<>();
        while (organizations.next()) {
            remainingIds.add(organizations.getInt("id"));
        }

        Iterator<Integer> iterator = organizationTable.keySet().iterator();
        while (iterator.hasNext()) {
            int id = iterator.next();
            if (!remainingIds.contains(id)) {
                iterator.remove();
            }
        }
        return new CommandExecute("Чистка коллекции завершена", true);
    }

}
