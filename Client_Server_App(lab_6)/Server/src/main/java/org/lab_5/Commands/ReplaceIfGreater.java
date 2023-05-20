package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.util.Hashtable;
import java.util.Scanner;

public class ReplaceIfGreater implements BaseCommand {
    ConsoleLog consoleLog = new ConsoleLog();
    private Hashtable<Integer, Organization> organizationTable;
    ;

    public ReplaceIfGreater(Hashtable organizationTable) {
        this.organizationTable = organizationTable;
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


    public CommandExecute execute(Request o) {
        int id = Integer.parseInt(o.getArg());
        String[] data = o.getData().split(" ");

        if (!organizationTable.containsKey(id)) {
            response = "Организации с id_" + id + "не существует";

        } else {


            if (data[0].equals("annualTurnover")) {
                while (true) {
                    Double updateD = Double.parseDouble(data[1]);
                    if (organizationTable.get(id).getAnnualTurnover() <= updateD) {
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
                    if (organizationTable.get(id).getEmployeesCount() <= updateInt) {
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
        return new CommandExecute(response,success);

    }
}
