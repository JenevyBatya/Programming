package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.ConsoleLog;
import org.lab.Models.Organization;
import org.lab.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class ReplaceIfGreater implements BaseCommand {
    ConsoleLog consoleLog = new ConsoleLog();
    private Hashtable<Integer, Organization> organizationTable;
    private Integer userId;
    private Connection connection;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public ReplaceIfGreater(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
        this.connection = connection;
    }

    private static String name = "replace_if_greater";
    private String response;
    private boolean success;

    private String description = name + " id: замена значения по ключу, если новое значение больше старого\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public CommandExecute execute(Request o) throws SQLException {
        int id = Integer.parseInt(o.getArg());
        String[] data = o.getData().split(" ");

        String sql1 = "select annualTurnover,employeesCount  from organization where (id=? and user_id=?)";
        PreparedStatement idToDelete = connection.prepareStatement(sql1);
        idToDelete.setInt(1, id);
        idToDelete.setInt(2, userId);
        ResultSet resultSet = idToDelete.executeQuery();



        if (!resultSet.next()) {
            response = "Организации с id_" + id + "не существует или вы не являетесь создателем записи";

        } else {


            if (data[0].equals("annualTurnover")) {
                while (true) {
                    Double updateD = Double.parseDouble(data[1]);

                    if (resultSet.getDouble("annualTurnover") <= updateD) {
                        String changeAnnualTurnover_sql = "update organization set annualTurnover = ? where (id=? and user_id=?)";
                        PreparedStatement changeAnnualTurnover = connection.prepareStatement(changeAnnualTurnover_sql);
                        changeAnnualTurnover.setDouble(1,updateD);
                        changeAnnualTurnover.setInt(2, id);
                        changeAnnualTurnover.setInt(3, userId);
                        changeAnnualTurnover.executeUpdate();
                        organizationTable.get(id).setAnnualTurnover(updateD);
                        response = "Значение annualTurnover было успешно заменено";
                        success = true;
                        break;
                    } else {
                        response = "Введенное значение меньше нынешнего";
                        success = false;
                    }
                }

            } else if (data[0].equals("employeeCount")) {
                while (true) {
                    Integer updateInt = Integer.parseInt(data[1]);
                    String changeEmployessCount_sql = "update organization set employeesCount = ? where (id=? and user_id=?)";
                    PreparedStatement changeEmployessCount = connection.prepareStatement(changeEmployessCount_sql);
                    changeEmployessCount.setInt(1,updateInt);
                    changeEmployessCount.setInt(2, id);
                    changeEmployessCount.setInt(3, userId);
                    changeEmployessCount.executeUpdate();
                    if (resultSet.getInt("employeesCount") <= updateInt) {
                        organizationTable.get(id).setEmployeesCount(updateInt);
                        response = "Значение employeeCount было успешно заменено";
                        success = true;
                        break;
                    } else {
                        response = "Введенное значение меньше нынешнего";
                        success = false;

                    }
                }
            }
        }
        return new CommandExecute(response, success);

    }
}
