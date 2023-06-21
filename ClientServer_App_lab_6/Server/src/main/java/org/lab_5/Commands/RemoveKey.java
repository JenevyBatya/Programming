package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.Models.Organization;
import org.lab_5.Request;

import java.util.Hashtable;

public class RemoveKey implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;;

    public RemoveKey(Hashtable organizationTable) {
        this.organizationTable = organizationTable;
    }

    private static String name = "remove_key";

    private String description = name + " id: удаление элемента из коллекции по его ключу\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandExecute execute(Request o) {
        try {
            int id = Integer.parseInt(o.getArg());
            if (organizationTable.containsKey(id)) {
                organizationTable.remove(id);
                return new CommandExecute("Организация с id_" + id + " была успешно удалена из реестра",true);
            } else {
                return new CommandExecute("Организации с id_" + id + " не существует",false);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return new CommandExecute("Неправильный синтаксис команды. Укажите id организации, которую вы хотите удалить, после команды",false);
        }

    }

}
