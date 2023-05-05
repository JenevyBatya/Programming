package org.lab_5.Commands;

import org.lab_5.CommandExecute;
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
    CommandExecute commandExecute;

    public CommandExecute execute(Object... o){
        try {
            //TODO

            int id = Integer.parseInt(o[0].toString());

            if (organizationTable.containsKey(id)) {
                OrganizationRegistration organizationRegistration = new OrganizationRegistration();
                Organization organization = organizationRegistration.registerNewOrganization(id, organizationTable, "update");
                if (organization.getId() != 0) {
                    organizationTable.put(id, organization);
                    commandExecute = new CommandExecute("Данные организации были успешно обновлены", true);
                }
            } else {
                commandExecute = new CommandExecute("Организации с id_" + id + " не существует", false);
            }
        }catch (ArrayIndexOutOfBoundsException e){

            return new CommandExecute("Неправильный синтаксис команды. Укажите id организации, данные которой вы хотите обновить",false);
        }
        return commandExecute;
    }

}
