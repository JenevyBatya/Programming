package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.ConsoleLog;
import org.lab_5.Models.Organization;

import java.util.Hashtable;
import java.util.Scanner;

public class ReplaceIfGreater implements BaseCommand{
    ConsoleLog consoleLog = new ConsoleLog();
    private Hashtable<Integer, Organization> organizationTable;;
    public ReplaceIfGreater(Hashtable organizationTable){
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


    public CommandExecute execute(Object... o) {
        try {
            int id = Integer.parseInt(o[0].toString());
            if (!organizationTable.containsKey(id)) {
                response="Организации с id_" + id + "не существует";

            } else {


                Scanner sc = new Scanner(System.in);
                consoleLog.consoleResp("Информацию по какому полю вы хотите поменять?\n" + "annualTurnover/employeeCount");
                String field = sc.nextLine();
                if (field.equals("annualTurnover")) {
                    while(true) {
                        try {
                            consoleLog.consoleResp("Введите новое или старое(" + organizationTable.get(id).getAnnualTurnover() + ") значение:");
//                            System.out.println("Введите новое или старое(" + organizationTable.get(id).getAnnualTurnover() + ") значение:");
                            String update = sc.nextLine();
                            Double updateD = Double.parseDouble(update);
                            if (organizationTable.get(id).getAnnualTurnover() <= updateD) {
                                organizationTable.get(id).setAnnualTurnover(updateD);
                                consoleLog.consoleResp("Значение annualTurnover было успешно заменено");
                                success=true;
                                break;
                            } else {
                                consoleLog.consoleResp("Введенное значение меньше нынешнего");
                            }
                        } catch (NumberFormatException e) {
                            consoleLog.consoleResp("Неправильный формат записи, требуется Double");

                        }

                    }
                } else if (field.equals("employeeCount")) {
                    while(true) {
                        try {
                            consoleLog.consoleResp("Введите новое или старое(" + organizationTable.get(id).getEmployeesCount() + ") значение:");
                            String update = sc.nextLine();
                            Integer updateInt = Integer.parseInt(update);

                            if (organizationTable.get(id).getEmployeesCount() <= updateInt) {
                                organizationTable.get(id).setEmployeesCount(updateInt);
                                response="Значение employeeCount было успешно заменено";
                                success=true;
                                break;
                            } else {
                                consoleLog.consoleResp("Введенное значение меньше нынешнего");

                            }
                        } catch (NumberFormatException e) {
                            consoleLog.consoleResp("Неправильный формат записи, требуется Integer");

                        }
                    }
                } else {
                    response="Введенное значение недоступно для изменения, вам доступен выбор между annualTurnover и employeeCount";
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            response="Неправильный синтаксис команды. Укажите id организации, у которой хотите обновить значения на большие";
            return new CommandExecute(response,false);
        }

        return new CommandExecute(response,success);
    }

}
