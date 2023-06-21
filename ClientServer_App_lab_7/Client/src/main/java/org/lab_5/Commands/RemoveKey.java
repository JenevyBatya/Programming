package org.lab_5.Commands;

import org.lab_5.CommandExecute;
import org.lab_5.Models.Organization;

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

    public CommandExecute execute(Object... o) {
        try {
            int id = Integer.parseInt(o[0].toString());
            return new CommandExecute(name,true);
        }catch (NumberFormatException e){
            return new CommandExecute("Неправильный формат id",false);

        }catch (ArrayIndexOutOfBoundsException e){
            return new CommandExecute("Неправильный синтаксис команды. Укажите id организации, которую вы хотите удалить, после команды",false);
        }

    }

}
