package org.lab_5.Commands;

import org.lab_5.CommandsManager;
import org.lab_5.Models.Organization;

import java.util.Hashtable;

public class Help implements BaseCommand{

    private Hashtable<Integer, Organization> organizationTable;;
    public Help(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "help";
    private String description = name + ": вывод справки по доступным командам для исполнения\n";


    public static String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public void execute(Object... o) {
        CommandsManager commandsManager = new CommandsManager(organizationTable,null);
        commandsManager.collectionOfCommands();
        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
        for(String command: commandHashtable.keySet()){
            if (command.equals("import")){continue;}
            System.out.println(commandHashtable.get(command).getDescription());
        }

    }
    @Override
    public void execute() {

    }
}
