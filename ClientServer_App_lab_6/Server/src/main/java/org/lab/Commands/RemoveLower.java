package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.Models.Organization;
import org.lab.Request;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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


    public CommandExecute execute(Request o) {
        int counter = 0;
        try {
            List<Integer> keysList = new ArrayList<>(organizationTable.keySet());
            int id = Integer.parseInt(o.getArg());
            for (int removeKey : keysList) {
                if (removeKey < id) {
                    organizationTable.remove(removeKey);
                    counter++;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return new CommandExecute("Неправильный синтаксис команды. Укажите id, при котором организации, имеющие id ниже заданного, будут удалены из коллекции",false);
        }
        if (counter==1){
            response="Была удалена 1 organization из коллекции";
        }else{
            response="Было удалено " + counter + " organizations из коллекции";
        }
        return new CommandExecute(response,true);
    }

}
