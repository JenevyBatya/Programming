package org.lab.Commands;

import org.lab.CommandExecute;
import org.lab.CommandsManager;
import org.lab.Models.Organization;
import org.lab.Request;

import java.sql.Connection;
import java.util.Hashtable;

public class Help implements BaseCommand {

    private Hashtable<Integer, Organization> organizationTable;
    ;

    public Help(Hashtable organizationTable, Connection connection) {
        this.organizationTable = organizationTable;
    }

    private static String name = "help";
    private String description = name + ": вывод справки по доступным командам для исполнения\n";


    public static String getName() {
        return name;
    }

    private String response = "";

    @Override
    public String getDescription() {
        return description;
    }
    public void setUserId(Integer userId) {

    }


    public CommandExecute execute(Request o) {
        CommandsManager commandsManager = new CommandsManager(organizationTable, null,null);
        commandsManager.collectionOfCommands();


        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
        for (String command : commandHashtable.keySet()) {
            if (command.equals("import")) {
                continue;
            }
            response += commandHashtable.get(command).getDescription();
        }

        return new CommandExecute(response, true);
    }
}
