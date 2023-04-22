package org.lab_5.Commands;

import org.lab_5.Models.Organization;
import org.lab_5.OrganizationRegistration;
import java.util.Hashtable;

public class Update implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    public Update(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "update";

    private String description = name + ":  обновление значения элемента коллекции, id которого равен заданному\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(Object... o){
        try {

            int id = Integer.parseInt(o[0].toString());
            if (organizationTable.containsKey(id)) {
                OrganizationRegistration organizationRegistration = new OrganizationRegistration();
                Organization organization = organizationRegistration.registerNewOrganization(id, organizationTable, "update");
                if (organization.getId() != 0) {
                    organizationTable.put(id, organization);
                }
            } else {
                System.out.println("Организации с id_" + id + " не существует");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Неправильный синтаксис команды. Укажите id организации, данные которой вы хотите обновить");
        }
    }
    @Override
    public void execute() {

    }
}
