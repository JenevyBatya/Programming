package org.lab_5.Commands;

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

    public void execute(Object... o) {
        try {
            int id = Integer.parseInt(o[0].toString());
            if (organizationTable.containsKey(id)) {
                organizationTable.remove(id);
                System.out.println("Организация с id_" + id + " была успешно удалена из реестра");
            } else {
                System.out.println("Организации с id_" + id + " не существует");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Неправильный синтаксис команды. Укажите id организации, которую вы хотите удалить, после команды");
        }
    }
}
