package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;

import java.util.Hashtable;

public class RemoveLower implements BaseCommand {
    private Hashtable<Integer, Organization> organizationTable;;

    public RemoveLower(Hashtable organizationTable) {
        this.organizationTable = organizationTable;
    }

    private static String name = "remove_lower";

    private String description = name + " id: удаление из коллекции всех элементов, меньших, чем заданные\n";

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    private String response;


    public CommandExecute execute(Object... o) {
        try {

            int id = Integer.parseInt(o[0].toString());
        }catch (NumberFormatException e){
            return new CommandExecute("Неправильный формат id",false);
        }catch (ArrayIndexOutOfBoundsException e){
            return new CommandExecute("Неправильный синтаксис команды. Укажите id, при котором организации, имеющие id ниже заданного, будут удалены из коллекции",false);
        }

        return new CommandExecute(name,true);
    }

}
