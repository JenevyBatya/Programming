package org.lab_5.Commands;

import org.lab_5.Models.Organization;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReplaceIfGreater implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    public ReplaceIfGreater(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "replace_if_greater";

    private String description = name + " id: замена значения по ключу, если новое значение больше старого\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public void execute(Object... o) {
        try {
            int id = Integer.parseInt(o[0].toString());
            if (!organizationTable.containsKey(id)) {
                System.out.println("Организации с id_" + id + "не существует");
            } else {


                Scanner sc = new Scanner(System.in);

                System.out.println("Информацию по какому полю вы хотите поменять?\n" + "annualTurnover/employeeCount");
                String field = sc.nextLine();
                if (field.equals("annualTurnover")) {
                    while(true) {
                        try {
                            System.out.println("Введите новое или старое(" + organizationTable.get(id).getAnnualTurnover() + ") значение:");
                            String update = sc.nextLine();
                            Double updateD = Double.parseDouble(update);
                            if (organizationTable.get(id).getAnnualTurnover() <= updateD) {
                                organizationTable.get(id).setAnnualTurnover(updateD);
                                System.out.println("Значение annualTurnover было успешно заменено");
                                break;
                            } else {
                                System.out.println("Введенное значение меньше нынешнего");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неправильный формат записи, требуется Double");
                        }
                    }
                } else if (field.equals("employeeCount")) {
                    while(true) {
                        try {
                            System.out.println("Введите новое или старое(" + organizationTable.get(id).getEmployeesCount() + ") значение:");
                            String update = sc.nextLine();
                            Integer updateInt = Integer.parseInt(update);

                            if (organizationTable.get(id).getEmployeesCount() <= updateInt) {
                                organizationTable.get(id).setEmployeesCount(updateInt);
                                System.out.println("Значение employeeCount было успешно заменено");
                                break;
                            } else {
                                System.out.println("Введенное значение меньше нынешнего");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Неправильный формат записи, требуется Integer");

                        }
                    }
                } else {
                    System.out.println("Введенное значение недоступно для изменения, вам доступен выбор между annualTurnover и employeeCount");
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Неправильный синтаксис команды. Укажите id организации, у которой хотите обновить значения на большие");
        }

    }
}
