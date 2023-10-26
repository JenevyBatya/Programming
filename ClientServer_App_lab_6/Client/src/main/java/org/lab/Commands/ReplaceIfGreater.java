package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.ConsoleLog;
import org.lab.Models.Organization;

import java.util.Hashtable;
import java.util.Scanner;

public class ReplaceIfGreater implements BaseCommand, CommandWithDetails {
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

    private String organizationData = "";


    public CommandExecute execute(Object... o) {
        try {
            int id = Integer.parseInt(o[0].toString());
            Scanner sc = new Scanner(System.in);
            consoleLog.consoleResp("Информацию по какому полю вы хотите поменять?\n" + "annualTurnover/employeeCount");
            String field = sc.nextLine();
            if (field.equals("annualTurnover")) {
                while (true) {
                    try {
                        consoleLog.consoleResp("Введите новое или старое значения:");
//                            System.out.println("Введите новое или старое(" + organizationTable.get(id).getAnnualTurnover() + ") значение:");
                        String update = sc.nextLine();
                        Double updateD = Double.parseDouble(update);
                        organizationData += field + " " + update;
                        return new CommandExecute(organizationData,true);

                    } catch (NumberFormatException e) {
                        consoleLog.consoleResp("Неправильный формат записи, требуется Double");

                    }

                }
            } else if (field.equals("employeeCount")) {
                while (true) {
                    try {
                        consoleLog.consoleResp("Введите новое или старое(" + organizationTable.get(id).getEmployeesCount() + ") значение:");
                        String update = sc.nextLine();
                        Integer updateInt = Integer.parseInt(update);
                        organizationData += field + " " + update;
                        return new CommandExecute(organizationData,true);
                    } catch (NumberFormatException e) {
                        consoleLog.consoleResp("Неправильный формат записи, требуется Integer");

                    }
                }
            } else {
                response = "Введенное значение недоступно для изменения, вам доступен выбор между annualTurnover и employeeCount";
                return new CommandExecute(response, false);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Неправильный синтаксис команды. Укажите id организации, у которой хотите обновить значения на большие";
            return new CommandExecute(response, false);
        }


    }

}
